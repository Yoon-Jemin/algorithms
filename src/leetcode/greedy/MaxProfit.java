package leetcode.greedy;

public class MaxProfit {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int profit = 0;
        int n = prices.length;

        for (int i = 0; i < n - 1; i++) {
            int now = prices[i];
            int next = prices[i + 1];

            if (now < next) profit += (next - now);
        }

        return profit;
    }
}
