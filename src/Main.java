import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentLinkedList studentList = new StudentLinkedList();
    private static final ActionStack actionStack = new ActionStack();
    private static final ServiceRequestQueue requestQueue = new ServiceRequestQueue();
    private static final StudentBST studentBST = new StudentBST();
    private static final HashStudentDirectory hashDirectory = new HashStudentDirectory();
    private static final CampusGraph campusGraph = new CampusGraph();

    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println(" UNIVERSITY STUDENT RECORD & CAMPUS ROUTE SYSTEM");
        System.out.println("====================================================");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice (1-16): ", 1, 16);

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> studentList.displayAll();
                case 5 -> addServiceRequest();
                case 6 -> processNextServiceRequest();
                case 7 -> actionStack.display();
                case 8 -> studentBST.displayInOrder();
                case 9 -> searchStudentUsingHashing();
                case 10 -> addCampusLocation();
                case 11 -> removeCampusLocation();
                case 12 -> addCampusConnection();
                case 13 -> removeCampusConnection();
                case 14 -> campusGraph.displayConnections();
                case 15 -> traverseCampusBFS();
                case 16 -> {
                    running = false;
                    System.out.println("\nThank you. System closed.");
                }
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n---------------- MAIN MENU ----------------");
        System.out.println("1.  Add Student Record");
        System.out.println("2.  Update Student Record");
        System.out.println("3.  Delete Student Record");
        System.out.println("4.  Display All Records using Linked List");
        System.out.println("5.  Add Service Request to Queue");
        System.out.println("6.  Process Next Service Request");
        System.out.println("7.  Display Recent Actions using Stack");
        System.out.println("8.  Display Students using BST");
        System.out.println("9.  Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS");
        System.out.println("16. Exit");
        System.out.println("--------------------------------------------");
    }

    private static void addStudent() {
        System.out.println("\n--- Add Student Record ---");
        String id = readNonEmpty("Student ID: ");

        if (studentList.find(id) != null) {
            System.out.println("Error: A student with this ID already exists.");
            return;
        }

        String name = readNonEmpty("Name: ");
        String programme = readNonEmpty("Programme: ");
        double marks = readDouble("Marks (0-100): ", 0, 100);

        Student student = new Student(id, name, programme, marks);
        studentList.add(student);
        studentBST.insert(student);
        hashDirectory.put(student);
        actionStack.push("Added student " + id + " (" + name + ")");

        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {
        System.out.println("\n--- Update Student Record ---");
        String id = readNonEmpty("Enter Student ID to update: ");
        Student student = studentList.find(id);

        if (student == null) {
            System.out.println("Student record not found.");
            return;
        }

        String name = readNonEmpty("New Name: ");
        String programme = readNonEmpty("New Programme: ");
        double marks = readDouble("New Marks (0-100): ", 0, 100);

       if (studentList.update(id, name, programme, marks)) {
    hashDirectory.put(student);
    actionStack.push("Updated student " + student.getStudentId());

    System.out.println("Student updated successfully.");
} else {
    System.out.println("Student update failed.");
}
    }

    private static void deleteStudent() {
        System.out.println("\n--- Delete Student Record ---");
        String id = readNonEmpty("Enter Student ID to delete: ");
        Student student = studentList.find(id);

        if (student == null) {
            System.out.println("Student record not found.");
            return;
        }

        studentList.remove(id);
        studentBST.delete(id);
        hashDirectory.remove(id);
        actionStack.push("Deleted student " + id + " (" + student.getName() + ")");

        System.out.println("Student deleted successfully.");
    }

    private static void addServiceRequest() {
        System.out.println("\n--- Add Service Request ---");
        String id = readNonEmpty("Student ID: ");

        if (studentList.find(id) == null) {
            System.out.println("Student record not found. Add the student first.");
            return;
        }

        String service = readNonEmpty("Service requested: ");
        requestQueue.add(id, service);
        actionStack.push("Added service request for " + id + ": " + service);
        System.out.println("Service request added to the queue.");
        requestQueue.display();
    }

    private static void processNextServiceRequest() {
        System.out.println("\n--- Process Next Service Request ---");
        ServiceRequestQueue.ServiceRequest request = requestQueue.processNext();

        if (request == null) {
            System.out.println("No pending service requests.");
            return;
        }

        actionStack.push("Processed service request: " + request);
        System.out.println("Processed: " + request);
    }

    private static void searchStudentUsingHashing() {
        System.out.println("\n--- Search Student using Hashing ---");
        String id = readNonEmpty("Enter Student ID: ");
        Student student = hashDirectory.get(id);

        if (student == null) {
            System.out.println("Student record not found in hash table.");
        } else {
            System.out.println("Student found using hashing:");
            System.out.println(student);
        }
    }

    private static void addCampusLocation() {
        System.out.println("\n--- Add Campus Location ---");
        String location = readNonEmpty("Location name: ");

        if (campusGraph.addLocation(location)) {
            actionStack.push("Added campus location: " + location);
            System.out.println("Campus location added successfully.");
        } else {
            System.out.println("Error: Location is empty or already exists.");
        }
    }

    private static void removeCampusLocation() {
        System.out.println("\n--- Remove Campus Location ---");
        String location = readNonEmpty("Location name: ");

        if (campusGraph.removeLocation(location)) {
            actionStack.push("Removed campus location: " + location);
            System.out.println("Campus location removed successfully.");
        } else {
            System.out.println("Location not found.");
        }
    }

    private static void addCampusConnection() {
        System.out.println("\n--- Add Campus Connection/Road ---");
        String from = readNonEmpty("First location: ");
        String to = readNonEmpty("Second location: ");

        if (campusGraph.addConnection(from, to)) {
            actionStack.push("Added campus connection: " + from + " <-> " + to);
            System.out.println("Connection added successfully.");
        } else {
            System.out.println("Connection could not be added. Check that both locations exist, are different, and are not already connected.");
        }
    }

    private static void removeCampusConnection() {
        System.out.println("\n--- Remove Campus Connection/Road ---");
        String from = readNonEmpty("First location: ");
        String to = readNonEmpty("Second location: ");

        if (campusGraph.removeConnection(from, to)) {
            actionStack.push("Removed campus connection: " + from + " <-> " + to);
            System.out.println("Connection removed successfully.");
        } else {
            System.out.println("Connection not found or one/both locations do not exist.");
        }
    }

    private static void traverseCampusBFS() {
        System.out.println("\n--- BFS Traversal ---");
        String start = readNonEmpty("Starting location: ");
        campusGraph.displayBFS(start);
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // Re-prompt below.
            }
            System.out.println("Please enter a whole number between " + min + " and " + max + ".");
        }
    }

    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                double number = Double.parseDouble(value);
                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // Re-prompt below.
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }


}
