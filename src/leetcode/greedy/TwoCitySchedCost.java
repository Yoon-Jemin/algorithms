package leetcode.greedy;

import java.util.PriorityQueue;

public class TwoCitySchedCost {

    public static void main(String[] args) {
        int[][] costs = {
                {259, 770},
                {448, 54},
                {926, 667},
                {184, 139},
                {840, 118},
                {577, 469}
        };

        System.out.println(twoCitySchedCost(costs));
    }

    public static int twoCitySchedCost(int[][] costs) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int diffA = Math.abs(a[0] - a[1]);
            int diffB = Math.abs(b[0] - b[1]);
            return Integer.compare(diffB, diffA);
        });

        for (int[] cost : costs) {
            pq.offer(cost);
        }

        int totalCost = 0;
        int limit = costs.length / 2;
        int group1 = 0;
        int group2 = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int a = cur[0];
            int b = cur[1];

            if (group1 == limit) {
                totalCost += b;
                group2++;
            } else if (group2 == limit) {
                totalCost += a;
                group1++;
            } else {
                if (a > b) {
                    totalCost += b;
                    group2++;
                } else {
                    totalCost += a;
                    group1++;
                }
            }
        }

        return totalCost;
    }
}
