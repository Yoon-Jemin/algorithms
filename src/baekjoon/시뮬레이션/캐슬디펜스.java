package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 캐슬디펜스 {

    static int N;
    static int M;
    static int Dist;
    static int[][] map;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Dist = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M];

        List<int[]> enemies =new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) enemies.add(new int[]{i, j});
            }
        }

        simulate(0, new ArrayList<>(), enemies);
        System.out.println(max);
    }

    private static void simulate(int index, List<Integer> archers, List<int[]> enemies) {
        if (archers.size() == 3) {
            startGame(copyMap(map, archers), enemies, archers);
            return;
        }

        for (int i = index; i < M; i++) {
            archers.add(i);
            simulate(i + 1, archers, enemies);
            archers.remove(archers.size() - 1);
        }
    }

    private static int[][] copyMap(int[][] map, List<Integer> archers) {
        int[][] copy = new int[N + 1][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = map[i][j];
            }
        }

        for (int archer : archers) {
            copy[N][archer] = 2;
        }

        return copy;
    }

    private static void startGame(int[][] map, List<int[]> enemies, List<Integer> archers) {
        int enemyKilled = 0;

        List<int[]> currentEnemies = new ArrayList<>();
        for (int[] e : enemies) currentEnemies.add(new int[]{e[0], e[1]});

        while (!currentEnemies.isEmpty()) {
            // 궁수들이 공격할 적 선정
            List<int[]> targets = new ArrayList<>();
            for (int archerCol : archers) {
                int[] target = findTarget(currentEnemies, archerCol);
                if (target != null) targets.add(target);
            }

            // 적 제거
            boolean[][] removed = new boolean[N][M];
            for (boolean[] r : removed) Arrays.fill(r, false);
            for (int[] target : targets) {
                if (!removed[target[0]][target[1]]) {
                    removed[target[0]][target[1]] = true;
                    enemyKilled++;
                }
            }
            List<int[]> survivors = new ArrayList<>();
            for (int[] e : currentEnemies) {
                if (!removed[e[0]][e[1]]) survivors.add(e);
            }

            // 적 이동
            List<int[]> moved = new ArrayList<>();
            for (int[] e : survivors) {
                if (e[0] + 1 < N) {
                    moved.add(new int[]{e[0] + 1, e[1]});
                }
            }
            currentEnemies = moved;
        }

        max = Math.max(max, enemyKilled);
    }

    private static int[] findTarget(List<int[]> enemies, int archerCol) {
        int minDist = Integer.MAX_VALUE;
        int[] target = null;

        for (int[] e : enemies) {
            int dist = (N - e[0]) + Math.abs(e[1] - archerCol);
            if (dist <= Dist) {
                if (dist < minDist || (dist == minDist && e[1] < target[1])) {
                    minDist = dist;
                    target = e;
                }
            }
        }

        return target;
    }
}
