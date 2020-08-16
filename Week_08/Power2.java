/**
 * Created by yuping on 2020/8/14.
 * LeetCode 231 2 的次幂 https://leetcode-cn.com/problems/power-of-two/
 */
public class Power2 {
    public boolean isPowerOfTwo(int n) {
        if(n<=0) return false;
        return (n&(n-1)) == 0;

//        return ((n & (n-1))==0 && n>0);
    }

    public static void main(String[] args) {
        Power2 power2 = new Power2();

        System.out.println(power2.isPowerOfTwo(2));
        System.out.println(power2.isPowerOfTwo(3));

    }
}
