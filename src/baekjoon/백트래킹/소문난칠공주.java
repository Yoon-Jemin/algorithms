package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 소문난칠공주 {
    static int answer = 0;
    static char[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력 받기
        for (int i = 0; i < 5; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        combination(new ArrayList<>(), 0);
        System.out.println(answer);
    }

    private static void combination(List<Integer> selected, int start) {
        if (selected.size() == 7) {
            if (isValid(selected)) answer++;
            return;
        }

        for (int i = start; i < 25; i++) {
            selected.add(i);
            combination(selected, i + 1);
            selected.remove(selected.size() - 1);
        }
    }

    private static boolean isValid(List<Integer> selected) {
        boolean[][] selectedMap = new boolean[5][5];
        int sCount = 0;

        for (int index : selected) {
            int x = index / 5;
            int y = index % 5;
            selectedMap[x][y] = true;
            if (map[x][y] == 'S') sCount++;
        }

        if (sCount < 4) return false;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] bfsVisited = new boolean[5][5];
        int count = 1;

        outer:
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (selectedMap[x][y]) {
                    queue.add(new int[]{x, y});
                    bfsVisited[x][y] = true;
                    break outer;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (selectedMap[nx][ny] && !bfsVisited[nx][ny]) {
                    bfsVisited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    count++;
                }
            }
        }

        return count == 7;
    }
}
