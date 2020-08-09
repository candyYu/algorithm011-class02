import java.util.Arrays;

/**
 * Created by yuping on 2020/8/4.
 * 552. 学生出勤记录 II
 */
public class StudentAttendRecord2 {

    //递归加记忆化搜索
    int _MOD = 1000000007;


    public int checkRecord(int n) {
        int[][][] f = new int[n + 1][2][3];

        f[0] = new int[][]{{1, 1, 1}, {1, 1, 1}};
        for (int i = 1; i <= n; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 3; k++) {
                    int val = f[i - 1][j][2]; // ...P
                    if (j > 0) val = (val + f[i - 1][j - 1][2]) % _MOD; // ...A
                    if (k > 0) val = (val + f[i - 1][j][k - 1]) % _MOD; // ...L
                    f[i][j][k] = val;
                }
        return f[n][1][2];
    }



    public int checkRecord2(int n) {
        //先写好递归，考虑记忆化搜索
        int[][][] memo = new int[n+1][2][3]; //第几表示签名第i个可被奖励数量
        for(int temp[][]:memo)
            for(int temp2[]:temp)
                Arrays.fill(temp2,-1);
        int ans = dfs(n,0,0,0,memo);
        return ans;
    }

    public int dfs(int n,int j,int a, int b,int [][][] memo) {
        //terminator
        if(memo[j][a][b] != -1) {
            return memo[j][a][b];
        }

        if(n == j) {
            return 1;
        }
        //proccess current level 可以选择 A L P，考虑按照找个顺序，写个三层循环
        int ans = 0;
        for(int i = 1; i <= 3; i++) {
            if(i == 1) {
                //加A
                if(a == 1) {
                    continue;
                }
                ans = (ans +dfs(n,j+1,1,0,memo))%_MOD;
            }else if (i == 2) {
                //加L
                if(b == 2) {
                    continue;
                }
                ans =  (ans + dfs(n,j+1,a,b+1,memo))%_MOD;
            }else if (i == 3) {
                //加上P
                ans =  (ans + dfs(n,j+1,a,0,memo))%_MOD;
            }
        }
        memo[j][a][b] = ans;
        return ans;
    }

    public int checkRecord3(int n) {
        //考虑动态规划，定义dp[n+1][2][3] 表示 i-1前有几个A 有几个L
        long[][][] dp = new long[n + 1][2][3]; // i, A:{0:non, 1:contains}, L:{0,1,2}
        // dp[1][..][..]
        dp[1][0][0] = 1;//1个没有A没有L
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;


        for (int i = 2; i <= n; i++) {

            // 当前这末尾+P
            // 无A末尾无L 可来自上一层  无A末尾无L   +     无A末尾1L   +    无A末尾2L
            // 0A0L     =                  0A0L      +       0A1L      +       0A2L
            dp[i][0][0] =          (dp[i - 1][0][0]  + dp[i - 1][0][1] + dp[i - 1][0][2]) % _MOD;
            // 同理，接着推
            // 1A0L     =       1A0L       +       1A1L      +       1A2L
            dp[i][1][0] = (dp[i - 1][1][0] + dp[i - 1][1][1] + dp[i - 1][1][2]) % _MOD;

            // +L
            // 0A1L = 0A0L
            dp[i][0][1] = dp[i - 1][0][0];
            // 0A2L = 0A1L
            dp[i][0][2] = dp[i - 1][0][1];
            // 1A1L = 1A0L
            dp[i][1][1] = dp[i - 1][1][0];
            // 1A2L = 1A1L
            dp[i][1][2] = dp[i - 1][1][1];


            // +A
            // 此处的 dp[i][1][0] 在上面 +P 时已经开始出现，故此处应 +=
            // 1A0L = 0A0L + 0A1L + 0A2L
            dp[i][1][0] += (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % _MOD;
        }


        // 返回第 n 层的六种情况
        return (int) ((dp[n][0][0] + dp[n][0][1] + dp[n][0][2] + dp[n][1][0] + dp[n][1][1] + dp[n][1][2]) % _MOD);


    }
}
