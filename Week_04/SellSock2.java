/**
 * Created by yuping on 2020/7/19.
 */
public class SellSock2 {

    public int maxProfit(int[] prices) {
        //比较符合常规，找峰谷然后进行交易
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxprofit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            maxprofit += peak - valley;
        }
        return maxprofit;


    }


}
