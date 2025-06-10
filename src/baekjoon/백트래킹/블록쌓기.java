package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 블록쌓기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] blockList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            blockList[i] = new ArrayList<>();
            blockList[i].add(0);
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                blockList[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // dp[i][j] = i번째 학생까지 고려했을 때 높이 j를 만드는 경우의 수
        int[][] dp = new int[n + 1][h + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= h; j++) {
                for (int block : blockList[i - 1]) {
                    if (j >= block) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - block]) % 10007;
                    }
                }
            }
        }

        System.out.println(dp[n][h]);
    }
}
