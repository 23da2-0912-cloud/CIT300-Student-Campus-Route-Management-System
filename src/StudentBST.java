public class StudentBST {
    private static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    public boolean insert(Student student) {
        if (root == null) {
            root = new Node(student);
            return true;
        }
        return insertRecursive(root, student);
    }

    private boolean insertRecursive(Node node, Student student) {
        int comparison = student.getStudentId().compareToIgnoreCase(node.student.getStudentId());

        if (comparison == 0) {
            return false;
        }

        if (comparison < 0) {
            if (node.left == null) {
                node.left = new Node(student);
                return true;
            }
            return insertRecursive(node.left, student);
        } else {
            if (node.right == null) {
                node.right = new Node(student);
                return true;
            }
            return insertRecursive(node.right, student);
        }
    }

    public Student search(String studentId) {
        Node current = root;
        while (current != null) {
            int comparison = studentId.compareToIgnoreCase(current.student.getStudentId());
            if (comparison == 0) {
                return current.student;
            }
            current = comparison < 0 ? current.left : current.right;
        }
        return null;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }
        System.out.println("\n--- Students in BST (In-Order by Student ID) ---");
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.student);
        inOrder(node.right);
    }

    public boolean delete(String studentId) {
        if (search(studentId) == null) {
            return false;
        }
        root = deleteRecursive(root, studentId);
        return true;
    }

    private Node deleteRecursive(Node node, String studentId) {
        if (node == null) return null;

        int comparison = studentId.compareToIgnoreCase(node.student.getStudentId());

        if (comparison < 0) {
            node.left = deleteRecursive(node.left, studentId);
        } else if (comparison > 0) {
            node.right = deleteRecursive(node.right, studentId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node successor = findMin(node.right);
            node.student = successor.student;
            node.right = deleteRecursive(node.right, successor.student.getStudentId());
        }
        return node;
    }

    private Node findMin(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public void rebuildFrom(StudentLinkedList list) {
        root = null;
        // This method intentionally rebuilds the tree from the linked list.
        // The StudentLinkedList does not expose nodes, so Main handles rebuild by
        // using a temporary export array through toArray().
        Student[] students = list.toArray();
        for (Student student : students) {
            insert(student);
        }
    }
}
