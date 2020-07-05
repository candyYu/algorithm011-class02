import java.util.PriorityQueue;

/**
 * Created by yuping on 2020/7/5.
 */
public class UglyNumber {

     //使用小顶堆符合作为本周题目要求 时间复杂度高O(nlogn)
     public int nthUglyNumber(int n) {
         long cur = 0L;
         PriorityQueue<Long> q = new PriorityQueue<>();
         q.offer(1L);
         while(n > 0){
             if(q.peek() != cur){
                 cur = q.poll();
                 n--;
                 q.add(cur*2);
                 q.add(cur*3);
                 q.add(cur*5);
             }
             else{
                 q.poll();
             }
         }
         return (int)cur;
     }

    //动态规划 效率高 时间复杂度O(n)
    public int nthUglyNumberMethod2(int n) {

        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(dp[a]*2,dp[b]*3),dp[c]*5);
            if (dp[i] == dp[a]*2) a++;
            if (dp[i] == dp[b]*3) b++;
            if (dp[i] == dp[c]*5) c++;
        }
        return dp[n-1];

    }
}
