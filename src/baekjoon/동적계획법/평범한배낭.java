package baekjoon.동적계획법;

import java.util.Arrays;
import java.util.Scanner;

public class 평범한배낭 {

    public static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   // 물품의 개수
        int maxWeight = sc.nextInt();

        int[] weights = new int[n + 1];
        int[] values = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            weights[i] = sc.nextInt();
            values[i] = sc.nextInt();
        }

        int[][] dp  = new int[n + 1][maxWeight + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= maxWeight; w++) {
                if (weights[i] > w) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i]] + values[i]);
                }
            }
        }

        System.out.println(dp[n][maxWeight]);
    }
}
