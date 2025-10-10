package programmers.level_2;

public class 타일링3xN {

    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    public static long MOD = 1000000007;
    public static int solution(int n) {
        if (n % 2 != 0) return 0;
        if (n == 2) return 3;

        long[] dp = new long[n + 1];
        long[] sum = new long[n + 1];
        dp[0] = 1;
        dp[2] = 3;
        sum[0] = dp[0];
        sum[2] = dp[0] + dp[2];

        for (int i = 4; i <= n; i += 2) {
            dp[i] = (dp[i - 2] * 3 + sum[i - 4] * 2) % MOD;
            sum[i] = (sum[i - 2] + dp[i]) % MOD;
        }

        return (int) dp[n];
    }
}
