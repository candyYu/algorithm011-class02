

/**
 * Created by yuping on 2020/8/14.
 * LeetCode 190 https://leetcode-cn.com/problems/reverse-bits/
 */
public class ReverseBits {

    public int reverseBits(int n) {
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    public int reverseBits2(int n) {
        int res = 0;
        //取出第i 放到第 32-i位中
        for(int i = 0; i < 32; i++) {
            res += (n >> i & 1) << (31-i);
        }
        return res;

    }

    public static void main(String[] args) {
        ReverseBits reverseBits = new ReverseBits();
        int n = 964176192;
        System.out.println(reverseBits.reverseBits(n));
        System.out.println(reverseBits.reverseBits2(n));
    }
}
