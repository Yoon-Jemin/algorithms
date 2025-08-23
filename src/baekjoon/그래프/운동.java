package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 운동 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 마을의 개수
        int m = Integer.parseInt(st.nextToken());   // 도로의 개수

        int[][] map = new int[n + 1][n + 1];
        for (int[] row : map) Arrays.fill(row, -1);

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            map[a][b] = distance;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][k] != -1 && map[k][j] != -1) {
                        if (map[i][j] == -1) map[i][j] = map[i][k] + map[k][j];
                        else map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] != -1 && map[j][i] != -1) answer = Math.min(answer, map[i][j] + map[j][i]);
            }
        }

        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }
}
