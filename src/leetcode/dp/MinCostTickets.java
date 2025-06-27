package leetcode.dp;

import java.util.LinkedList;
import java.util.Queue;

public class MinCostTickets {

    public static void main(String[] args) {
        int[] days = {1,4,6,7,8,20};
        int[] costs = {2,7,15};
        System.out.println(mincostTickets(days, costs));
    }

    public static int mincostTickets(int[] days, int[] costs) {
        int lastday = days[days.length - 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int day : days) queue.add(day);

        int[] dp = new int[lastday + 1];  // 마지막 날짜가 i 일 때 최솟값
        int start = queue.poll();
        dp[start] = Math.min(costs[0], Math.min(costs[1], costs[2]));

        int next = (queue.isEmpty()) ? start : queue.poll();

        for (int i = start + 1; i <= lastday; i++) {
            if (i == next) {    // 티켓을 사야함
                int buy_1 = dp[i - 1] + costs[0];
                int buy_7 = (i >= 7) ? dp[i - 7] + costs[1] : costs[1];
                int buy_30 = (i >= 30) ? dp[i - 30] + costs[2] : costs[2];

                dp[i] = Math.min(buy_1, Math.min(buy_7, buy_30));

                if (!queue.isEmpty()) next = queue.poll();
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[lastday];
    }
}
