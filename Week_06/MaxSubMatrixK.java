import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by yuping on 2020/8/2.
 * 363. 矩形区域不超过 K 的最大数值和
 */
public class MaxSubMatrixK {

    //动态规划详解
    public int maxSumSubmatrixWithDp(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
        for (int i1 = 1; i1 <= rows; i1++) {
            for (int j1 = 1; j1 <= cols; j1++) {
                int[][] dp = new int[rows + 1][cols + 1]; // renew  // from (i1,j1) to (i2,j2)
                dp[i1][j1] = matrix[i1 - 1][j1 - 1];
                for (int i2 = i1; i2 <= rows; i2++) {
                    for (int j2 = j1; j2 <= cols; j2++) {
                        dp[i2][j2] = dp[i2 - 1][j2] + dp[i2][j2 - 1] - dp[i2 - 1][j2 - 1] + matrix[i2 - 1][j2 - 1];
                        if (dp[i2][j2] <= k && dp[i2][j2] > max) max = dp[i2][j2];
                    }
                }
            }
        }
        return max;
    }



    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] rowSum = new int[matrix.length];
        int maxAera = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            Arrays.fill(rowSum, 0);
            for(int r = i; r < n; r++) {
                for(int j = 0; j < m; j++) {
                    rowSum[j] += matrix[j][r];
                }
                int tmpMax = getMax(rowSum, k);
                if(tmpMax < k) {
                    maxAera = Math.max(maxAera, tmpMax);
                }else if(tmpMax == k) {
                    return tmpMax;
                }
            }
        }
        return maxAera;
    }

    private int getMax(int[] rowSum, int k) {
        //平衡二叉树
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int max = Integer.MIN_VALUE, sum = 0;
        for(int i = 0; i < rowSum.length; i++) {
            sum += rowSum[i];
            if(set.ceiling(sum - k) != null) {
                int tmp = set.ceiling(sum - k);
                max = Math.max(max, sum - tmp);
            }
            set.add(sum);
        }
        return max;
    }

}
