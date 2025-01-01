package programmers.level_2;

public class 피보나치수 {

    public static int solution(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int temp = dp[i - 2] + dp[i - 1];
            dp[i] = temp % 1234567;
        }

        return dp[n];
    }

}
