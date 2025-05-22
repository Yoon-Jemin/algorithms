package leetcode.dp;

import java.util.PriorityQueue;

public class NthUglyNumber {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber2(10));
    }

    public static int nthUglyNumber(int n) {
        int[] primes = {2, 3, 5};
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.add(1l);

        int idx = 0;
        long num = 0;
        while (idx != n) {
            num = pq.poll();
            while (!pq.isEmpty() && num == pq.peek()) num = pq.poll();
            idx++;
            if (idx == n) break;

            for (int i = 0; i < primes.length; i++) {
                pq.add(num * primes[i]);
            }
        }


        return (int) num;
    }

    public static int nthUglyNumber2(int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        int count2 = 0;
        int count3 = 0;
        int count5 = 0;

        for (int i = 0; i < n - 1; i++) {
            int next2 = dp[count2] * 2;
            int next3 = dp[count3] * 3;
            int next5 = dp[count5] * 5;

            int min = Math.min(next2, Math.min(next3, next5));

            if (min == next2) count2++;
            if (min == next3) count3++;
            if (min == next5) count5++;

            dp[i + 1] = min;
        }

        return dp[n - 1];
    }
}
