package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 인구이동 {

    static int N;
    static int[][] map;
    static int limit1;
    static int limit2;
    static int unitedTotal = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        limit1 = Integer.parseInt(st.nextToken());
        limit2 = Integer.parseInt(st.nextToken());

        for (int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isMoved = true;
        int answer = 0;

        while (isMoved) {
            isMoved = false;
            boolean[][] visited = new boolean[N][N];
            for (boolean[] v : visited) Arrays.fill(v, false);

            // DFS로 연합 구하기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;

                    unitedTotal = 0;
                    List<int[]> unitedCities = DFS(i, j, visited, new ArrayList<>());

                    if (unitedCities.size() <= 1) continue;

                    isMoved = true;
                    int numUnitedCities = unitedCities.size();
                    int updatedPopulation = unitedTotal / numUnitedCities;

                    for (int[] city : unitedCities) {
                        map[city[0]][city[1]] = updatedPopulation;
                    }
                }
            }

            if (isMoved) answer++;
        }

        System.out.println(answer);
    }

    private static List<int[]> DFS (int x, int y, boolean[][] visited, List<int[]> cities) {
        cities.add(new int[] {x, y});
        visited[x][y] = true;
        unitedTotal += map[x][y];

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;
            if (isPossible(map[x][y], map[nx][ny])) {
                DFS(nx, ny, visited, cities);
            }
        }

        return cities;
    }

    private static boolean isPossible (int population1, int population2) {
        int diff = Math.abs(population1 - population2);
        return limit1 <= diff && diff <= limit2;
    }
}
