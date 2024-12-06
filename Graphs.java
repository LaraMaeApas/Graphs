import java.util.*;

public class CampusMapNavigation {
    private Map<String, List<String>> campusMap;

    public CampusMapNavigation() {
        campusMap = new HashMap<>();
    }

    
    public void addBuilding(String building) {
        campusMap.putIfAbsent(building, new ArrayList<>());
    }

   
    public void addPathway(String building1, String building2) {
        if (!campusMap.containsKey(building1) || !campusMap.containsKey(building2)) {
            System.out.println("One or both buildings do not exist.");
            return;
        }
        campusMap.get(building1).add(building2);
        campusMap.get(building2).add(building1);
    }

    
    public void findShortestRoute(String start, String destination) {
        if (!campusMap.containsKey(start) || !campusMap.containsKey(destination)) {
            System.out.println("One or both buildings do not exist.");
            return;
        }

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(Arrays.asList(start));
        visited.add(start);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String current = path.get(path.size() - 1);

            if (current.equals(destination)) {
                System.out.println("Shortest route: " + String.join(" -> ", path));
                return;
            }

            for (String neighbor : campusMap.get(current)) {
                if (!visited.contains(neighbor)) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                    visited.add(neighbor);
                }
            }
        }

        System.out.println("No route found.");
    }

    public static void main(String[] args) {
        CampusMapNavigation navigation = new CampusMapNavigation();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nCampus Map Navigation");
            System.out.println("1. Add Building");
            System.out.println("2. Add Pathway");
            System.out.println("3. Find Shortest Route");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter building name: ");
                    String building = scanner.nextLine();
                    navigation.addBuilding(building);
                    System.out.println("Building added!");
                    break;

                case 2:
                    System.out.print("Enter first building name: ");
                    String building1 = scanner.nextLine();
                    System.out.print("Enter second building name: ");
                    String building2 = scanner.nextLine();
                    navigation.addPathway(building1, building2);
                    System.out.println("Pathway added!");
                    break;

                case 3:
                    System.out.print("Enter starting building name: ");
                    String start = scanner.nextLine();
                    System.out.print("Enter destination building name: ");
                    String destination = scanner.nextLine();
                    navigation.findShortestRoute(start, destination);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
