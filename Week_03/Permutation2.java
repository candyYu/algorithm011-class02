import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yuping on 2020/7/12.
 */
public class Permutation2 {

    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> ans = new ArrayList();
        Arrays.sort(nums);
        backtrace(nums,ans,new ArrayList(),new boolean[nums.length]);
        return ans;

    }

    private void backtrace(int [] nums, List<List<Integer>> res, List<Integer> path, boolean [] used) {

        //terminator
        if (path.size() == nums.length) {
            res.add(new ArrayList(path));
        }

        //divide 分治 回溯
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i >0 && nums[i] == nums[i-1] && !used[i-1]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backtrace(nums,res,path,used);

            used[i] = false;
            path.remove(path.size()-1);

        }

    }

    public static void main(String[] args) {
        Permutation2 permutation2 = new Permutation2();
        List<List<Integer>> res = permutation2.permuteUnique(new int[]{3,3,0,3});
        System.out.println(res);
    }

}
