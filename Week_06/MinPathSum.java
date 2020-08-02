/**
 * Created by yuping on 2020/8/2.
 * 64. 最小路径和
 */
public class MinPathSum {

    public int minPathSum(int[][] grid) {
        //动态规划
        //定义dp[i][j]，总0，0到i，j的路径最小和
        //dp[i-1][j]  dp[i][j-1] 走过来，min(x,x)+grid[i][j]
        //边界条件判断完整
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int [][] dp = new int[m][n];
        //dp[m][n] 就是我们的解
        dp[0][0] = grid[0][0];
        for(int i = 1; i < m; i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
            }

        }
        return dp[m-1][n-1];

    }
}
