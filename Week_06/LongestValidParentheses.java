import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yuping on 2020/7/31.
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        //考虑动态规划
        // dp[i] 表示以s[i]为结尾的有小括号子串长度，如果s[i] = '(' dp[i] = 0
        // dp[0] = 0;
        // 如果s[i] = ")" 看s[i-1] 如果为（ 则结果为dp[i-2]+2
        //如果为s[i-1]为 ")"    (dp[i-2])) 那看前面 s[i-dp[i-1]-1] 的值如果为( 则为
        //dp[i] = dp[i-1]+2 + dp[i-dp[i-1]-2] //看i-dp[i-1]-2 >0

        int n = s.length();
        int [] dp = new int[n];
        int res = 0;
        for(int i = 1; i < n ; i++) {
            if(i > 0 && s.charAt(i) == ')') {
                if(s.charAt(i-1) == '(') {
                    dp[i] = (i >=2 ? dp[i-2]:0) + 2;
                }else{
                    if(i-dp[i-1]-1 >=0 && s.charAt(i-dp[i-1]-1) == '(') {
                        if( i > dp[i-1]+2 ) {
                            dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2];
                        }else {
                            dp[i] = dp[i-1] + 2;
                        }
                    }
                }
            }
            res = Math.max(res,dp[i]);
        }

        return res;

    }

    public int longestValidParenthesesUseStack(String s) {
        //使用栈来求解
        Deque<Integer> stack = new ArrayDeque();
        stack.push(-1);
        int max = 0;
        for(int i = 0;i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                //入栈
                stack.push(i);
            }else {
                //为） 需要出栈
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                }else{
                    max = Math.max(max,i-stack.peek());
                }

            }
        }
        return max;

    }

}
