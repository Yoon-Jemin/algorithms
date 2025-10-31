package baekjoon.동적계획법;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 더하기 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int max = 0;
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int next = sc.nextInt();
            list.add(next);
            max = Math.max(max, next);
        }

        int[][] dp = new int[max + 1][4];

        dp[0][0] = dp[0][1] = dp[0][2] = dp[0][3] = 1;
        for (int i = 1; i <= max; i++) {
            dp[i][1] = dp[i - 1][1];

            dp[i][2] = dp[i][1];
            if (i >= 2) {
                dp[i][2] += dp[i - 2][2];
            }

            dp[i][3] = dp[i][2];
            if (i >= 3) {
                dp[i][3] += dp[i - 3][3];
            }
        }

        for (int l : list) {
            System.out.println(dp[l][3]);
        }
    }
}
