import java.util.ArrayDeque;
import java.util.Deque;

public class ActionStack {
    private final Deque<String> stack = new ArrayDeque<>();

    public void push(String action) {
        stack.push(action);
    }

    public String pop() {
        return stack.isEmpty() ? null : stack.pop();
    }

    // Returns the most recent action without removing it
    public String peek() {
        return stack.isEmpty() ? null : stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public void display() {
        if (stack.isEmpty()) {
            System.out.println("No recent actions.");
            return;
        }

        System.out.println("\n--- Recent Actions (Stack) ---");
        int number = 1;

        for (String action : stack) {
            System.out.println(number + ". " + action);
            number++;
        }
    }
}