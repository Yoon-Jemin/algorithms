package baekjoon.동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());

        while (C-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[][] map = new int[2][N];
            int[][] dp = new int[2][N];

            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 초기 세팅
            dp[0][0] = map[0][0];
            dp[1][0] = map[1][0];

            if (N > 1) {
                dp[0][1] = map[0][1] + dp[1][0];
                dp[1][1] = map[1][1] + dp[0][0];
            }

            for (int i = 2; i < N; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + map[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + map[1][i];
            }

            System.out.println(Math.max(dp[0][N - 1], dp[1][N - 1]));
        }
    }
}
