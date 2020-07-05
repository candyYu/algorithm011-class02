import java.util.HashMap;


/**
 * Created by yuping on 2020/7/5.
 */
public class TwoSumWithHash {

    public int[] twoSum(int []nums,int target) {
        HashMap<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int prefect = target - nums[i];
            if (map.containsKey(prefect)) {
                return  new int[]{i,map.get(prefect)};
            }
            map.put(nums[i],i);
        }

        throw new IllegalArgumentException("No two sum solution");

    }
}
