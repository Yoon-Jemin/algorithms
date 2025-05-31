package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 그림 {

    static int n;
    static int m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        int[][] visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 0;
        int biggest = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0 && arr[i][j] == 1) {
                    visited[i][j] = 1;
                    int area = dfs(i, j, visited, arr);
                    num++;
                    biggest = Math.max(biggest, area);
                }
            }
        }

        System.out.println(num);
        System.out.println(biggest);
    }

    private static int dfs(int x, int y, int[][] visited, int[][] arr) {
        int area = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] != 0) continue;
            if (arr[nx][ny] == 1) {
                visited[nx][ny] = 1;
                area += dfs(nx, ny, visited, arr);
            }
        }

        return area;
    }
}
