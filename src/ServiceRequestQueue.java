import java.util.ArrayDeque;
import java.util.Queue;

public class ServiceRequestQueue {
    public static class ServiceRequest {
        private final String studentId;
        private final String service;

        public ServiceRequest(String studentId, String service) {
            this.studentId = studentId;
            this.service = service;
        }

        @Override
        public String toString() {
            return "Student ID: " + studentId + " | Service: " + service;
        }
    }

    private final Queue<ServiceRequest> queue = new ArrayDeque<>();

    public void add(String studentId, String service) {
        queue.offer(new ServiceRequest(studentId, service));
    }

    public ServiceRequest processNext() {
        return queue.poll();
    }

    public void display() {
        if (queue.isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }

        System.out.println("\n--- Pending Service Requests (Queue) ---");
        int number = 1;
        for (ServiceRequest request : queue) {
            System.out.println(number + ". " + request);
            number++;
        }
    }

    public int size() {
        return queue.size();
    }
}
