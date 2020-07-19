import java.util.Arrays;

/**
 * Created by yuping on 2020/7/19.
 */
public class AssignCookie {

    public int findContentChildren(int[] g, int[] s) {
        int count = 0;
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0, j = 0;
        while (i < s.length && j < g.length) {
            //饼干可以满足孩子胃口就给了
            if(s[i] >= g[j]){
                i++;
                j++;
                count++;
            }else{
                i++;
            }
        }

        return count;

    }

}
