/**
 * Created by yuping on 2020/7/19.
 */
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        int lo = 0;
        int ri = row*col-1;
        int mid,r,c;

        while(lo <= ri) {

            mid = lo + (ri - lo)/2;
            r = mid / col;
            c = mid % col;


            if (target == matrix[r][c]) {
                return true;
            }else if (target > matrix[r][c]) {
                lo = mid + 1;
            }else {
                ri = mid -1;
            }

        }

        return false;


    }
}
