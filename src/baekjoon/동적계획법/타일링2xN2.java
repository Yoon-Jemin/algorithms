package baekjoon.동적계획법;

import java.util.Scanner;

public class 타일링2xN2 {

    static int MOD = 10007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        if (n == 1) {
            System.out.println(1);
            return;
        } else if (n == 2) {
            System.out.println(3);;
            return;
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2) % MOD;
        }

        System.out.println(dp[n]);
    }
}
