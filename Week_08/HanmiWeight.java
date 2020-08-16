/**
 * Created by yuping on 2020/8/14.
 * LeetCode 191 https://leetcode-cn.com/problems/number-of-1-bits/solution/wei-1de-ge-shu-by-leetcode/
 */
public class HanmiWeight {


    public int hammingWeight(int n) {
        int count = 0;
        while(n!=0){
            //最低位1设置为0
            n &= (n - 1);
            count++;
        }

        return count;

    }


    public int hammingWeight2(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
        }
        return bits;
    }


    public static void main(String[] args) {
        HanmiWeight hanmiWeight = new HanmiWeight();

        int count = hanmiWeight.hammingWeight(3);

        System.out.println(count);
    }


}
