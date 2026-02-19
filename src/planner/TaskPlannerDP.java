package planner;

import model.Task;
import java.util.List;

public class TaskPlannerDP {

    // Calculates maximum revenue using Dynamic Programming
    // and prints the DP table
    public int calculateMaxRevenue(List<Task> tasks) {

        int n = tasks.size();
        int maxDays = 5;

        // dp[i][d] = max revenue using first i tasks within d days
        int[][] dp = new int[n + 1][maxDays + 1];

        for (int i = 1; i <= n; i++) {
            Task task = tasks.get(i - 1);

            for (int d = 1; d <= maxDays; d++) {

                // Case 1: skip current task
                dp[i][d] = dp[i - 1][d];

                // Case 2: take current task if deadline allows
                if (task.getDeadline() >= d) {
                    dp[i][d] = Math.max(
                            dp[i][d],
                            dp[i - 1][d - 1] + task.getRevenue()
                    );
                }

                // Case 3: carry forward previous day's revenue (IMPORTANT FIX)
                dp[i][d] = Math.max(dp[i][d], dp[i][d - 1]);
            }
        }

        // Print DP table
        System.out.println("\nDP TABLE (rows = tasks, cols = days)");
        for (int i = 0; i <= n; i++) {
            for (int d = 0; d <= maxDays; d++) {
                System.out.print(dp[i][d] + "\t");
            }
            System.out.println();
        }

        // Final optimal revenue
        return dp[n][maxDays];
    }
}
