/**
 * Created by yuping on 2020/7/19.
 */
public class JumpGame2 {

    public int jump(int[] nums) {
        //正向跳跃
        // int maxPosition = 0;
        // int len = nums.length;
        // int end = 0;
        // int step = 0;
        // for(int i = 0; i < len-1; i++) {
        //     maxPosition = Math.max(maxPosition,nums[i]+i);
        //     if(i == end) {
        //         end = maxPosition;
        //         step++;
        //     }

        // }
        // return step;

        //反向跳跃
        int position = nums.length - 1; //要找的位置
        int steps = 0;
        while (position != 0) { //是否到了第 0 个位置
            for (int i = 0; i < position; i++) {
                if (nums[i] >= position - i) {
                    position = i; //更新要找的位置
                    steps++;
                    break;
                }
            }
        }
        return steps;

    }
}
