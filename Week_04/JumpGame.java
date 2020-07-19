/**
 * Created by yuping on 2020/7/19.
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        // maintain a reachablePosition variable.
        int reachablePosition = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > reachablePosition) break;
            reachablePosition = Math.max(reachablePosition, i + nums[i]);
            if (reachablePosition >= nums.length - 1) return true;
        }
        return false;
    }

    public boolean canJump2(int[] nums) {
        int maxIndex = nums.length-1;
        int maxJump  = nums[0];
        for(int i = 0; i <= maxJump; i++){
            maxJump=Math.max(maxJump,i+nums[i]);
            if(maxJump>=maxIndex) return true;
        }
        return false;
    }

}
