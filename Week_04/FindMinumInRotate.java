/**
 * Created by yuping on 2020/7/19.
 */
public class FindMinumInRotate {

    public int findMin(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }


        int lo = 0,ri = nums.length-1;
        int mid;

        if (nums[ri] >= nums[lo]) {
            return nums[lo];
        }

        while(lo <= ri) {
            mid = lo + (ri - lo)/2;

            if(nums[mid] > nums[mid+1]) {
                return nums[mid+1];
            }

            if(nums[mid-1] > nums[mid]) {
                return nums[mid];
            }

            if (nums[mid] > nums[0]) {
                lo = mid + 1;
            } else {
                ri = mid - 1;
            }
        }

        return -1;
    }

    public int findMinMethod2(int[] nums) {
        int lo=0,hi=nums.length-1;
        // find the index of the smallest value using binary search.
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
        while(lo<hi){
            int mid=(lo+hi)/2;
            if(nums[mid]>nums[hi]) lo=mid+1;
            else hi=mid;
        }
        return nums[lo];
    }
}
