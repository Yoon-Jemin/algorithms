package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 뿌요뿌요 {

    static char[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];

        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        List<List<int[]>> sameList = simulate();
        while (!sameList.isEmpty()) {
            for (List<int[]> same : sameList) remove(same);
            fall();
            answer++;
            sameList = simulate();
        }

        System.out.println(answer);
    }

    private static List<List<int[]>> simulate() {
        List<List<int[]>> sameList = new ArrayList<>();
        int[][] visited = new int[12][6];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] == '.') continue;

                List<int[]> same = check(i, j, visited);
                if (same.size() >= 4) {
                    sameList.add(same);
                }
            }
        }

        return sameList;
    }

    private static List<int[]> check(int i, int j, int[][] visited) {
        char letter = map[i][j];
        List<int[]> list = new ArrayList<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i, j});
        visited[i][j] = 1;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];

            list.add(new int[] {x, y});

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) continue;
                if (visited[nx][ny] == 1) continue;

                if (map[nx][ny] == letter) {
                    queue.add(new int[] {nx, ny});
                    visited[nx][ny] = 1;
                }
            }
        }

        return list;
    }

    private static void remove(List<int[]> remove) {
        for (int[] r : remove) {
            map[r[0]][r[1]] = '.';
        }
    }

    private static void fall() {
        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] == '.') continue;

                int I = i;
                int J = j;
                while (I + 1 <= 11 && map[I + 1][J] =='.') {
                    map[I + 1][J] = map[I][J];
                    map[I][J] = '.';

                    I = I + 1;
                }
            }
        }
    }
}
