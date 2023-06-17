import java.util.Scanner;

public class TSPDynamicProgramming {
    private static int[][] dp;
    private static int[][] distance;
    private static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        n = scanner.nextInt();

        distance = new int[n + 1][n + 1];
        dp = new int[1 << (n + 1)][n + 1];

        System.out.println("Enter the distances between cities: ");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                distance[i][j] = scanner.nextInt();
            }
        }

        int minCost = tsp(1, 1);

        System.out.println("Minimum cost of TSP tour: " + minCost);

        scanner.close();
    }

    private static int tsp(int mask, int pos) {
        if (mask == (1 << (n + 1)) - 1) {
            // All cities have been visited, return the cost of returning to the starting
            // city
            return distance[pos][1];
        }

        if (dp[mask][pos] != 0) {
            // The optimal cost for the current subproblem has already been computed
            return dp[mask][pos];
        }

        int minCost = Integer.MAX_VALUE;

        for (int city = 1; city <= n; city++) {
            if ((mask & (1 << city)) == 0) {
                // City has not been visited yet
                int newCost = distance[pos][city] + tsp(mask | (1 << city), city);
                minCost = Math.min(minCost, newCost);
            }
        }

        dp[mask][pos] = minCost;

        return minCost;
    }
}
