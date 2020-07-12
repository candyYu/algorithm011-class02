import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuping on 2020/7/12.
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> res = new ArrayList();
        combine_recursion(n,k,1,res,new ArrayList());
        return res;

    }

    private void combine_recursion(int n,int k,int first,List<List<Integer>> res,List<Integer> path) {
        //terminator
        if (path.size() == k) {
            res.add(new ArrayList(path));
        }

        // for 循环 加上下面递归，需要回溯
        for (int i = first; i <= n; i++) {
            path.add(i);
            combine_recursion(n,k,i+1,res,path);
            path.remove(path.size()-1);
        }
    }



}
