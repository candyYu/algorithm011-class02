/**
 * Created by yuping on 2020/6/27.
 */
public class TrapRainWater {

    public int trap(int[] height) {
        //1.暴力法 ,计算左 右两侧最高高度， min(height[left],height[right])-height[i];
        // int  ans = 0;
        // for (int i = 0;i < height.length;i++) {
        //      int left_max = height[0];
        //      int right_max = height[height.length - 1];
        //      for( int j = i;j > 0;j--) {
        //           left_max = Math.max(left_max,height[j]);
        //      }
        //      for (int j = i;j < height.length;j++) {
        //          right_max = Math.max(right_max,height[j]);
        //      }
        //      ans = ans + Math.min(left_max,right_max) - height[i];
        // }

        // return ans;


        //2.栈
        // Stack<Integer> stack = new Stack<Integer>();
        // int ans = 0;
        // for (int i = 0;i < height.length;i++) {
        //     while(!stack.isEmpty() &&  height[stack.peek()] <= height[i]) {
        //         int top = stack.peek();
        //         stack.pop();
        //         if (stack.isEmpty()) {
        //             break;
        //         }
        //         int distance = i - stack.peek()-1;
        //         int boundHeight = Math.min(height[stack.peek()],height[i]) - height[top];
        //         ans = ans+ distance * boundHeight;
        //     }
        //     stack.push(i);
        // }
        // return ans;


        //3.双指针
        int len = height.length;
        if (len < 3) return 0;
        int left = 0;
        int right = len-1;
        int l_max = height[0];
        int r_max = height[len - 1];
        int ans = 0;

        while (left < right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            // ans += min(l_max, r_max) - height[i]
            if (l_max < r_max) {
                ans += l_max - height[left];
                left++;
            } else {
                ans += r_max - height[right];
                right--;
            }
        }
        return ans;





        //4.动态规划,存储right_max的值
        // 特判
        // int len = height.length;
        // if (len < 3) {
        //     return 0;
        // }
        // int[] left_max = new int[len];
        // int[] right_max = new int[len];
        // right_max[len-1] = height[len-1];
        // int ans = 0;
        // for (int i = 1;i < len;i++) {
        //     left_max[i] = Math.max(left_max[i-1],height[i-1]);
        // }
        // for (int i = len-2;i >= 0;i--) {
        //     right_max[i] = Math.max(right_max[i+1],height[i+1]);
        // }


        // for (int i = 0;i < len;i++) {
        //     ans = ans + Math.max(Math.min(left_max[i],right_max[i]) - height[i],0);
        // }
        // return ans;



    }
}
