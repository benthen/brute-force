import java.util.Scanner;

public class BruteForce {
    private static int[][] cityDistance;
    private static int[] path;
    private static int cityNum;
    private static int minDistance;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of cities to be traveled: ");
        cityNum = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        String[] cityNames = new String[cityNum];
        for (int i = 0; i < cityNum; i++) {
            System.out.printf("Enter the name of city %d: ", i + 1);
            cityNames[i] = scanner.nextLine();
        }
        cityDistance = new int[cityNum][cityNum]; // Initialize cityDistance to have dimension of cityNum x cityNum
        path = new int[cityNum]; // Initialize path with numCity. eg 3 cities, then 3 paths (a->b->c->a)
        minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                if (i != j) {
                    System.out.printf("Enter the distance from %s to %s: ", cityNames[i], cityNames[j]);
                    cityDistance[i][j] = scanner.nextInt();
                } else {
                    cityDistance[i][j] = 0;
                }
            }
        }
        initializePath();
        generatePermutations(1); // Start with city 1 since city 0 is fixed
        System.out.println("\n\nMinimum distance traveled: " + minDistance);
        System.out.println("Path taken: ");
        printPath(cityNames);
        scanner.close();
    }

    public static void initializePath() {
        for (int i = 0; i < cityNum; i++)
            path[i] = i;
    }

    public static void generatePermutations(int index) {
        if (index == cityNum) {
            int cost = calculateCost();
            if (cost < minDistance)
                minDistance = cost;
        }
        for (int i = index; i < cityNum; i++) {
            swap(index, i);
            generatePermutations(index + 1);
            swap(index, i);
        }
    }

    public static int calculateCost() {
        return calculateCostRecursively(0, 1, 0);
    }

    public static int calculateCostRecursively(int currentCity, int count, int currentCost) {
        if (count == cityNum) {
            return currentCost + cityDistance[currentCity][0];
        }
        int minCost = Integer.MAX_VALUE;
        for (int nextCity = 1; nextCity < cityNum; nextCity++) {
            if (path[nextCity] != -1) {
                int cost = cityDistance[currentCity][nextCity];
                path[nextCity] = -1;
                int temp = calculateCostRecursively(nextCity, count + 1, currentCost + cost);
                path[nextCity] = nextCity;
                minCost = Math.min(minCost, temp);
            }
        }
        return minCost;
    }

    public static void swap(int i, int j) {
        int temp = path[i];
        path[i] = path[j];
        path[j] = temp;
    }

    public static void printPath(String[] cityNames) {
        for (int i = 0; i < cityNum; i++) {
            System.out.print(cityNames[path[i]] + " -> ");
        }
        System.out.println(cityNames[path[0]]);
    }
}
