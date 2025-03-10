package programmers.level_2;

public class 타일링 {

    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    public static int solution(int n) {
        int[] dp = new int[n + 1];
        int mod = 1000000007;

        if (n == 1) return 1;
        if (n == 2) return 2;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] % mod  + dp[i - 2] % mod;
        }

        return dp[n] % mod;
    }
}
