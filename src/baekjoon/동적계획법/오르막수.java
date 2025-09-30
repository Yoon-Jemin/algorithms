package baekjoon.동적계획법;

import java.io.IOException;
import java.util.Scanner;

public class 오르막수 {

    static final int MOD = 10007;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n == 1) {
            System.out.println(10);
            return;
        }


        int[][] dp = new int[n + 1][10];    // i번쨰 자리수 : 끝 자리

        for (int j = 0; j <= 9; j++) dp[1][j] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                }
            }
        }

        int answer = 0;
        for (int j = 0; j <= 9; j++) {
            answer = (answer + dp[n][j]) % MOD;
        }

        System.out.println(answer);
    }
}
