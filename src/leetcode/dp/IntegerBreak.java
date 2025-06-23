package leetcode.dp;

public class IntegerBreak {

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }

    public static int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;

        for (int i = 5; i <= n; i++) {
            int a = i - 1;
            int b = 1;

            int max = dp[a] * dp[b];
            while (a >= b) {
                max = Math.max(max, dp[a] * dp[b]);
                a--;
                b++;
            }
            dp[i] = max;
        }

        return dp[n];
    }
}
