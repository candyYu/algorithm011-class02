/**
 * Created by yuping on 2020/7/19.
 */
public class SearchRotate {

    public int search(int[] nums, int target) {

        int lo = 0, right = nums.length-1;

        while(lo <= right) {
            int mid = lo + (right-lo)/2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[lo] > nums[mid]) {
                //说明有序的部分在后半段
                if( target >= nums[mid] && target <=nums[right]) {
                    lo = mid + 1;
                }else {
                    right = mid - 1;
                }

            } else {
                //说明有序部分在前半段
                if ( target <= nums[mid] && target >= nums[lo]) {
                    right = mid - 1;
                }else {
                    lo = mid + 1;
                }
            }
        }

        return -1;

    }

}
