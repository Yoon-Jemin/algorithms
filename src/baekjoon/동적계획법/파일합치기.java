package baekjoon.동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파일합치기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for (int t = 0; t < n; t++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            int[] files = new int[K];
            int[] prefix = new int[K + 1];
            int[][] dp = new int[K][K];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                files[i] = Integer.parseInt(st.nextToken());
                prefix[i + 1] = prefix[i] + files[i];
            }

            for (int len = 2; len <= K; len++) {
                for (int i = 0; i <= K - len; i++) {
                    int j = i + len - 1;

                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + (prefix[j + 1] - prefix[i]));
                    }
                }
            }

            System.out.println(dp[0][K - 1]);
        }
    }
}
