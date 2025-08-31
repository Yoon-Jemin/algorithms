package baekjoon.동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 퇴사2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] days = new int[n + 2];
        int[] pays = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            days[i] = Integer.parseInt(st.nextToken());
            pays[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 2];

        for (int i = n; i >= 1; i--) {

            dp[i] = dp[i + 1];
            if (i + days[i] <= n + 1) {
                dp[i] = Math.max(dp[i], pays[i] + dp[i + days[i]]);
            }
        }

        System.out.println(dp[1]);
    }
}
