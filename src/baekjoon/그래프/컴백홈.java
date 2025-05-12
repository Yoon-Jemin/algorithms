package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 컴백홈 {

    static int answer = 0;
    static int n,m,k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < m; j++) {
                if (s.charAt(j) == '.') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = 1;
                }
            }
        }

        map[n-1][0] = 1;
        dfs(n - 1, 0, 1, map);
        System.out.println(answer);
    }

    private static void dfs(int x, int y, int depth, int[][] map) {
        if (depth > k) return;

        if (x == 0 && y == m - 1) {
            if (depth == k) answer++;
            return;
        }

        int[] moveX = {0, 0, 1, -1};
        int[] moveY = {-1, 1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
            if (map[nextX][nextY] == 0) {
                map[nextX][nextY] = 1;
                dfs(nextX, nextY, depth + 1, map);
                map[nextX][nextY] = 0;
            }
        }
    }
}
