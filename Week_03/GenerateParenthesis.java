import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuping on 2020/7/7.
 */
public class GenerateParenthesis {


    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();
        _generate(0, 0, n, "",results);
        return results;
    }

    private void _generate(int left,int right,int n,String s,List<String> result) {
        //terminator
        if (left == n && right == n) {
            //filter the invalid s
            //判断有效括号，左括号数量<n  右括号数量 <前面左括号数量
            result.add(s);
            return;
        }

        //process
        //处理当前层，加左括号或者右括号
        if (left < n) {
            _generate(left+1, right, n, s + "(",result);
        }

        if (left >  right) {
            _generate(left, right+1, n, s + ")",result);
        }

        //drill down


        //reverse states 清除状态


    }


    public static void main(String[] args) {
        GenerateParenthesis gen = new GenerateParenthesis();
        gen.generateParenthesis(3);
    }


}
