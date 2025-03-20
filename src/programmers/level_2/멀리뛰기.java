package programmers.level_2;

public class 멀리뛰기 {
    public long solution(int n) {
        long[] dp = new long[n + 1];
        long mod = 1234567L;

        if (n == 1) return 1L;
        if (n == 2) return 2L;

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }

        return dp[n];
    }
}
