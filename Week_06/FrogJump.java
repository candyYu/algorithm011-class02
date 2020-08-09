import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by yuping on 2020/8/3.
 * 403. 青蛙过河
 */
public class FrogJump {

    public boolean canCross(int[] stones) {
        //记忆化搜索，递归
        int n = stones.length;
        int[][] memo = new int[n][n];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return recursion(stones,0,0,memo) == 1;

    }

    public int recursion(int[] stones,int ind,int jumpsize,int[][] memo) {

        //terminator
        if (memo[ind][jumpsize] >= 0) {
            return memo[ind][jumpsize];
        }
        for (int i = ind + 1; i < stones.length; i++) {
            int gap = stones[i] - stones[ind];
            if (gap >= jumpsize - 1 && gap <= jumpsize + 1) {
                if (recursion(stones, i, gap, memo) == 1) {
                    memo[ind][gap] = 1;
                    return 1;
                }
            }
        }
        memo[ind][jumpsize] = (ind == stones.length - 1) ? 1 : 0;
        return memo[ind][jumpsize];

    }


    public boolean canCross2(int[] stones) {
        if (stones.length == 0) {
            return true;
        }

        HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>(stones.length);
        map.put(0, new HashSet<Integer>());
        map.get(0).add(1);
        for (int i = 1; i < stones.length; i++) {
            map.put(stones[i], new HashSet<Integer>() );
        }

        for (int i = 0; i < stones.length - 1; i++) {
            int stone = stones[i];
            for (int step : map.get(stone)) {
                int reach = step + stone;
                if (reach == stones[stones.length - 1]) {
                    return true;
                }
                HashSet<Integer> set = map.get(reach);
                if (set != null) {
                    set.add(step);
                    if (step - 1 > 0) set.add(step - 1);
                    set.add(step + 1);
                }
            }
        }

        return false;
    }

}
