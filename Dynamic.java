import java.util.Arrays;

public class Dynamic {
    
    private static int tsp(int[][] graph, int startCity) {
        int n = graph.length;
        int[][] dp = new int[1 << n][n];
        
        // Initialize dp table with maximum values
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        dp[1 << startCity][startCity] = 0;
        
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int currentCity = 0; currentCity < n; currentCity++) {
                if ((mask & (1 << currentCity)) != 0) {
                    for (int nextCity = 0; nextCity < n; nextCity++) {
                        if (nextCity != currentCity && (mask & (1 << nextCity)) != 0) {
                            int prevMask = mask ^ (1 << currentCity);
                            dp[mask][currentCity] = Math.min(dp[mask][currentCity], dp[prevMask][nextCity] + graph[nextCity][currentCity]);
                        }
                    }
                }
            }
        }
        
        int minCost = Integer.MAX_VALUE;
        for (int endCity = 0; endCity < n; endCity++) {
            if (endCity != startCity) {
                minCost = Math.min(minCost, dp[(1 << n) - 1][endCity] + graph[endCity][startCity]);
            }
        }
        
        return minCost;
    }
    
    public static void main(String[] args) {
        int[][] graph = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };
        
        int startCity = 0;
        int minCost = tsp(graph, startCity);
        System.out.println("Minimum cost: " + minCost);
    }
}
