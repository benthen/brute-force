import java.util.Scanner;

public class TSPDivideAndConquer {
    private static int[][] cityDistance;
    private static int cityNum;
    private static int minDistance;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        cityNum = scanner.nextInt();

        cityDistance = new int[cityNum][cityNum];

        System.out.println("Enter the distance matrix:");
        for (int i = 0; i < cityNum; i++) {
            for (int j = 0; j < cityNum; j++) {
                cityDistance[i][j] = scanner.nextInt();
            }
        }

        minDistance = Integer.MAX_VALUE;

        int[] cities = new int[cityNum];
        for (int i = 0; i < cityNum; i++) {
            cities[i] = i;
        }

        divideAndConquer(cities, 0, cityNum - 1);

        System.out.println("Minimum distance: " + minDistance);
    }

    private static void divideAndConquer(int[] cities, int start, int end) {
        if (start == end) {
            int cost = calculateCost(cities);
            if (cost < minDistance) {
                minDistance = cost;
            }
        } else {
            for (int i = start; i <= end; i++) {
                swap(cities, start, i);
                divideAndConquer(cities, start + 1, end);
                swap(cities, start, i);
            }
        }
    }

    private static int calculateCost(int[] cities) {
        int totalCost = 0;
        for (int i = 0; i < cityNum - 1; i++) {
            totalCost += cityDistance[cities[i]][cities[i + 1]];
        }
        totalCost += cityDistance[cities[cityNum - 1]][cities[0]]; // Return to the starting city
        return totalCost;
    }

    private static void swap(int[] cities, int a, int b) {
        int temp = cities[a];
        cities[a] = cities[b];
        cities[b] = temp;
    }
}
