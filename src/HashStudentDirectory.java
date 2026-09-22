public class HashStudentDirectory {
    private static class HashNode {
        String key;
        Student value;
        HashNode next;

        HashNode(String key, Student value) {
            this.key = key;
            this.value = value;
        }
    }

    private final HashNode[] buckets;
    private int size;

    public HashStudentDirectory() {
        buckets = new HashNode[17];
    }

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = 31 * hash + Character.toLowerCase(key.charAt(i));
        }
        return Math.floorMod(hash, buckets.length);
    }

   public boolean put(Student student) {
    if (student == null || student.getStudentId() == null) {
        return false;
    }

    String key = student.getStudentId();
        int index = hash(key);
        HashNode current = buckets[index];

        while (current != null) {
            if (current.key.equalsIgnoreCase(key)) {
                current.value = student;
                return false;
            }
            current = current.next;
        }

        HashNode node = new HashNode(key, student);
        node.next = buckets[index];
        buckets[index] = node;
        size++;
        return true;
    }

    public Student get(String key) {
        int index = hash(key);
        HashNode current = buckets[index];
        while (current != null) {
            if (current.key.equalsIgnoreCase(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public boolean remove(String key) {
        int index = hash(key);
        HashNode current = buckets[index];
        HashNode previous = null;

        while (current != null) {
            if (current.key.equalsIgnoreCase(key)) {
                if (previous == null) {
                    buckets[index] = current.next;
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
}
