/**
 * Created by yuping on 2020/6/27.
 */
public class MoveZero {

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                //将非0元素放到j未知
                //i元素未知设为0，相当于交换
                nums[j] = nums[i];
                if (i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }


    }
}
