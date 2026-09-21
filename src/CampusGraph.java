import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CampusGraph {
    private final Map<String, List<String>> adjacencyList = new HashMap<>();

    private String normalize(String location) {
        return location.trim();
    }

    public boolean addLocation(String location) {
        location = normalize(location);
        if (location.isEmpty() || adjacencyList.containsKey(location)) {
            return false;
        }
        adjacencyList.put(location, new ArrayList<>());
        return true;
    }

    public boolean removeLocation(String location) {
        location = normalize(location);
        if (!adjacencyList.containsKey(location)) {
            return false;
        }

        adjacencyList.remove(location);
        for (List<String> neighbours : adjacencyList.values()) {
            neighbours.remove(location);
        }
        return true;
    }

    public boolean addConnection(String from, String to) {
        from = normalize(from);
        to = normalize(to);

        if (from.equalsIgnoreCase(to) || !adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            return false;
        }

        if (containsIgnoreCase(adjacencyList.get(from), to)) {
            return false;
        }

        adjacencyList.get(from).add(to);
        adjacencyList.get(to).add(from);
        return true;
    }

    public boolean removeConnection(String from, String to) {
        from = normalize(from);
        to = normalize(to);

        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            return false;
        }

        boolean removedOne = removeIgnoreCase(adjacencyList.get(from), to);
        boolean removedTwo = removeIgnoreCase(adjacencyList.get(to), from);
        return removedOne || removedTwo;
    }

    public void displayConnections() {
        if (adjacencyList.isEmpty()) {
            System.out.println("No campus locations have been added.");
            return;
        }

        System.out.println("\n--- Campus Network (Adjacency List) ---");
        for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public void displayBFS(String start) {
        String actualStart = findKeyIgnoreCase(start);
        if (actualStart == null) {
            System.out.println("Start location was not found.");
            return;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        queue.offer(actualStart);
        visited.add(actualStart);

        System.out.println("\n--- BFS Campus Traversal ---");
        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current);

            List<String> neighbours = adjacencyList.get(current);
            for (String neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.offer(neighbour);
                }
            }

            if (!queue.isEmpty()) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    private boolean containsIgnoreCase(List<String> list, String target) {
        for (String item : list) {
            if (item.equalsIgnoreCase(target)) return true;
        }
        return false;
    }

    private boolean removeIgnoreCase(List<String> list, String target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equalsIgnoreCase(target)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

    private String findKeyIgnoreCase(String target) {
        for (String key : adjacencyList.keySet()) {
            if (key.equalsIgnoreCase(target.trim())) {
                return key;
            }
        }
        return null;
    }
}
