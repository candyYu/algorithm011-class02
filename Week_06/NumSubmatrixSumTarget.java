import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuping on 2020/8/2.
 * 1074. Number of Submatrices That Sum to Target
 */
public class NumSubmatrixSumTarget {

    public int numSubmatrixSumTarget(int[][] matrix, int target) {

        int count = 0;
        int[] dp = new int[matrix[0].length];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(dp, 0);
            for (int j = i; j < matrix.length; j++) {
                map.clear();
                map.put(0, 1);
                int sum = 0;
                for (int k = 0; k < matrix[0].length; k++) {
                    dp[k] += sum += matrix[j][k];
                    count += map.getOrDefault(dp[k] - target, 0);
                    map.put(dp[k], map.getOrDefault(dp[k], 0) + 1);
                }
            }
        }
        return count;

    }



    public int numSubmatrixSumTarget2(int[][] A, int target) {
        int res = 0, m = A.length, n = A[0].length;
        for (int i = 0; i < m; i++)
            for (int j = 1; j < n; j++)
                A[i][j] += A[i][j - 1];
        Map<Integer, Integer> counter = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                counter.clear();
                counter.put(0, 1);
                int cur = 0;
                for (int k = 0; k < m; k++) {
                    cur += A[k][j] - (i > 0 ? A[k][i - 1] : 0);
                    res += counter.getOrDefault(cur - target, 0);
                    counter.put(cur, counter.getOrDefault(cur, 0) + 1);
                }
            }
        }
        return res;
    }

    public int numSubmatrixSumTargetSameWithLast(int[][] matrix, int target) {
        for(int i=0;i<matrix.length;i++){
            for(int j=1;j<matrix[0].length;j++){
                matrix[i][j] += matrix[i][j-1];
            }
        }

        // 核心思想：遍历所有的矩阵
        // 主要优化手段：记录子矩阵的和，这样中间矩阵的值，可采用大矩阵减去小矩阵
        int res = 0;
        for(int i=0;i<matrix[0].length;i++){
            for(int j=i;j<matrix[0].length;j++){
                Map<Integer,Integer> map = new HashMap<>();
                int pre = 0;
                for(int k=0;k<matrix.length;k++){
                    int val = pre + (i == 0 ? matrix[k][j] : matrix[k][j] - matrix[k][i-1]);
                    if(val == target){
                        res++;
                    }
                    res += map.getOrDefault(val-target,0);
                    map.put(val,map.getOrDefault(val,0)+1);
                    pre = val;
                }
            }
        }
        return res;
    }

//    O(m^2n^2)
    public int numSubmatrixSumTargetWith(int[][] matrix, int target) {
        int x = matrix.length;
        int y = matrix[0].length;
        int[][] dp = new int[x][y];
        dp[0][0]  = matrix[0][0];
        for(int i = 1 ;i<x;i++) dp[i][0] = dp[i-1][0] + matrix[i][0];
        for(int j = 1;j<y;j++) dp[0][j] = dp[0][j-1] + matrix[0][j];
        for(int i = 1;i<x;i++){
            for(int j = 1;j<y;j++){
                dp[i][j]  = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
            }
        }
        int curSum;
        int count = 0 ;
        for(int x1 = 0;x1<x;x1++){
            for(int x2 = x1;x2<x;x2++){
                for(int y1 = 0;y1<y;y1++){
                    for(int y2 = y1;y2<y;y2++){
                        if(x1==0&&y1==0) curSum = dp[x2][y2];
                        else if(x1==0) curSum = dp[x2][y2] - dp[x2][y1-1];
                        else if(y1==0) curSum = dp[x2][y2] - dp[x1-1][y2];
                        else curSum = dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2] + dp[x1-1][y1-1];
                        if(curSum==target){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

}
