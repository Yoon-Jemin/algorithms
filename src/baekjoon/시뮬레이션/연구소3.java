package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소3 {

    static int N, M;
    static int[][] map;
    static List<int[]> viruses;
    static int minTime = Integer.MAX_VALUE;
    static int land = 0;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());   // 놓을 수 있는 바이러스의 개수

        map = new int[N][N];
        viruses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) land++;
                if (map[i][j] == 2) viruses.add(new int[] {i, j});
            }
        }

        // 완전탐색 + 시뮬레이션
        find(0, new ArrayList<>());

        if (minTime == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minTime);
    }

    private static void find(int start, ArrayList<int[]> candidate) {
        if (candidate.size() == M) {
            simulate(candidate);
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            candidate.add(viruses.get(i));
            find(i + 1, candidate);
            candidate.remove(candidate.size() - 1);
        }
    }

    private static void simulate(ArrayList<int[]> candidate) {
        int[][] copyMap = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[i][j] = map[i][j];
                visited[i][j] = false;
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        for (int[] c : candidate) {
            queue.add(new int[] {c[0], c[1], 0});
            visited[c[0]][c[1]] = true;
        }

        int invaded = 0;
        int maxTime = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int x = poll[0];
            int y = poll[1];
            int time = poll[2];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (copyMap[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                if (copyMap[nx][ny] == 0) {
                    maxTime = Math.max(maxTime, time + 1);
                    invaded++;
                }

                queue.add(new int[] {nx, ny, time + 1});
            }
        }

        if (invaded == land) minTime = Math.min(minTime, maxTime);
     }

}
