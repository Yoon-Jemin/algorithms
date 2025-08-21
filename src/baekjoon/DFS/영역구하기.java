package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 영역구하기 {

    static int N;
    static int M;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visited = new boolean[M][N];
        for (boolean[] v : visited) Arrays.fill(v, false);

        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());  // 0
            int y1 = Integer.parseInt(st.nextToken());  // 2 -> 2
            int x2 = Integer.parseInt(st.nextToken());  // 4
            int y2 = Integer.parseInt(st.nextToken());  // 4

            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    map[x][y] = 1;
                }
            }
        }

        List<Integer> groups = new ArrayList<>();
        int groupCount = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    int size = DFS(i, j);
                    groups.add(size);
                    groupCount++;
                }
            }
        }

        System.out.println(groupCount);
        Collections.sort(groups);
        for (int g : groups) {
            System.out.print(g + " ");
        }
    }

    private static int DFS(int x, int y) {
        int answer = 1;
        visited[x][y] = true;

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if (visited[nx][ny]) continue;
            if (map[nx][ny] == 0) {
                answer += DFS(nx, ny);
            }
        }

        return answer;
    }
}
