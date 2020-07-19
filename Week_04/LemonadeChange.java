/**
 * Created by yuping on 2020/7/19.
 */
public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        //贪心，成倍数关系，先大的找
        int five = 0, ten = 0;
        for( int bill : bills) {
            if (bill == 5) {
                five++;
            }else if (bill == 10) {
                if (five <= 0) {
                    return false;
                }
                five--;
                ten++;
            }else if (bill == 20) {
                if (ten > 0 && five >0) {
                    ten--;
                    five--;
                }else if (five >= 3) {
                    five = five-3;
                }else {
                    return false;
                }
            }
        }
        return true;

    }

}
