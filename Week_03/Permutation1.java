import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuping on 2020/7/12.
 */
public class Permutation1 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res  = new ArrayList();
        List<Integer> path = new ArrayList();
        int n = nums.length;
        if (n == 0) {
            return res;
        }
        boolean[] used = new boolean[n];
        backtrace(nums,0,path,used,res);
        return res;
    }

    //回溯
    private void backtrace(int [] nums, int depth, List<Integer> path,boolean []used, List<List<Integer>> res) {

        //terminator
        if (depth == nums.length) {
            res.add(new ArrayList(path));
        }

        //divide
        for (int i = 0; i < nums.length; i++) {
            if(!used[i]) {
                used[i] = true;
                path.add(nums[i]);
                backtrace(nums,depth+1,path,used,res);

                //reverse state 回溯
                used[i] = false;
                path.remove(path.size()-1);
            }
        }

    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Permutation1 permutation1 = new Permutation1();
        List<List<Integer>> res = permutation1.permute(new int[]{1,2,3});
        System.out.println(res);
    }
}
