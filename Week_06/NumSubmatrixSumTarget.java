import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by yuping on 2020/8/2.
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
}
