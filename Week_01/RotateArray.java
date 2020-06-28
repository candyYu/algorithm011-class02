/**
 * Created by yuping on 2020/6/27.
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
        int numLen = nums.length;
        k = k%numLen;
        int count = 0;
        for(int start=0;count<nums.length;start++){
            int current = start;
            int prev = nums[start];
            do{
                int next = (current+k)%numLen;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            }while(start!=current);
        }
    }

}
