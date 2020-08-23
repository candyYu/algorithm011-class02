/**
 * Created by yuping on 2020/8/23.
 */
public class ReverseStrII {

    public String reverseStr(String s, int k) {
        char[] a = s.toCharArray();
        int len = s.length();
        for(int i = 0; i < len; i += 2*k) {
            int left = i, right = Math.min(i + k - 1, len - 1);
            while(left < right) {
                char temp = a[left];
                a[left++] = a[right];
                a[right--] = temp;
            }
        }

        return new String(a);


    }
}
