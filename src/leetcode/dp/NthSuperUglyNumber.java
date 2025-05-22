package leetcode.dp;

import java.util.Arrays;

public class NthSuperUglyNumber {

    public static void main(String[] args) {
        int n = 12;
        int[] primes = {2,7,13,19};
        System.out.println(nthSuperUglyNumber(n, primes));
    }

    public static int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int[] indices = new int[primes.length];
        long[] values = new long[primes.length];

        for (int i = 0; i < primes.length; i++) {
            values[i] = primes[i];
        }

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (long value : values) {
                min = (int) Math.min(min, value);
            }
            dp[i] = min;

            for (int j = 0; j < primes.length; j++) {
                if (values[j] == min) {
                    indices[j]++;
                    values[j] = (long) dp[indices[j]] * primes[j];
                }
            }
        }

        return dp[n - 1];
    }
}
