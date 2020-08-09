/**
 * Created by yuping on 2020/8/3.
 * 72. 编辑距离
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        //dp[i][j] 表示word1 0 到 i 转换到 word2 0 j 所需要的最少操作次数
        int m = word1.length();
        int n = word2.length();

        //有一个字符串为空串
        if(m * n == 0) {
            return m + n;
        }

        int [][] dp = new int[m+1][n+1];

        //base case 边界情况处理
        for(int i = 0; i <= m ; i++) {
            dp[i][0] = i;
        }

        for(int j = 0; j <= n; j++) {
            dp[0][j] =  j;
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                int left = dp[i-1][j] + 1;
                int up = dp[i][j-1] + 1;
                int corner = dp[i-1][j-1];
                if(word1.charAt(i-1) != word2.charAt(j-1)) {
                    corner += 1;
                }
                dp[i][j] = Math.min(Math.min(left,up),corner);

            }
        }

        return dp[m][n];

    }
}
