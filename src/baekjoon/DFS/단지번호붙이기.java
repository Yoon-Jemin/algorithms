package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 단지번호붙이기 {


    /**
     * 7
     * 0110100
     * 0110101
     * 1110101
     * 0000111
     * 0100000
     * 0111110
     * 0111000
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) {
                    int count = dfs(i, j, graph);
                    ans.add(count);
                }
            }
        }

        System.out.println(ans.size());
        Collections.sort(ans);
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
    }

    private static int dfs(int x, int y, int[][] graph) {
        int count = 1;
        graph[x][y] = 0;

        int[] move_X = {0, 0, 1, -1};
        int[] move_Y = {1, -1, 0, 0};
        for (int i = 0; i < 4; i++) {
            int nx = x + move_X[i];
            int ny = y + move_Y[i];
            if (nx < 0 || ny < 0 || nx >= graph.length || ny >= graph.length) continue;
            if (graph[nx][ny] == 1) {
                count += dfs(nx, ny, graph);
            }
        }

        return count;
    }
}
