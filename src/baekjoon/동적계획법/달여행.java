package baekjoon.동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 달여행 {

    /**
     * 6 4
     * 5 8 5 1
     * 3 5 8 4
     * 9 77 65 5
     * 2 1 5 2
     * 5 98 1 5
     * 4 95 67 58
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int[][][] dp = new int[n][m][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }

        for (int j = 0; j < m; j++) {
            for (int d= 0; d < 3; d++) {
                dp[0][j][d] = map[0][j];
            }
        }

        int[] dx = {-1, 0, 1};
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 3; d++) {
                    int prevCol = j + dx[d];
                    if (prevCol < 0 || prevCol >= m) continue;
                    for (int prevD = 0; prevD < 3; prevD++) {
                        if (d == prevD) continue;
                        dp[i][j][d] = Math.min(dp[i][j][d], map[i][j] + dp[i-1][prevCol][prevD]);
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            for (int d = 0; d < 3; d++) {
                answer = Math.min(answer, dp[n-1][j][d]);
            }
        }

        System.out.println(answer);
    }
}
