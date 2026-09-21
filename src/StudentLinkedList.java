public class StudentLinkedList {
    private static class Node {
        Student student;
        Node next;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node head;
    private int size;

    public boolean add(Student student) {
        if (find(student.getStudentId()) != null) {
            return false;
        }

        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    public Student find(String studentId) {
        Node current = head;
        while (current != null) {
            if (current.student.getStudentId().equalsIgnoreCase(studentId)) {
                return current.student;
            }
            current = current.next;
        }
        return null;
    }

    public boolean remove(String studentId) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.student.getStudentId().equalsIgnoreCase(studentId)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Student[] toArray() {
        Student[] students = new Student[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            students[index++] = current.student;
            current = current.next;
        }
        return students;
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n--- Student Records (Linked List) ---");
        Node current = head;
        while (current != null) {
            System.out.println(current.student);
            current = current.next;
        }
        System.out.println("Total records: " + size);
    }
}
