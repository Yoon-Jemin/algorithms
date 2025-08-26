package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 정원 {

    static int N;
    static int M;
    static int G, R;
    static int answer = 0;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        List<int[]> goodSoils = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) goodSoils.add(new int[]{i, j});
            }
        }

        find(0, new ArrayList<>(), goodSoils);
        System.out.println(answer);
    }

    private static void find(int start, List<Integer> selectedIdx, List<int[]> goodSoils) {
        if (selectedIdx.size() == G + R) {
            chooseGreenOrRed(0, new ArrayList<>(), selectedIdx, goodSoils);
        }

        for (int i = start; i < goodSoils.size(); i++) {
            int[] goodSoil = goodSoils.get(i);
            selectedIdx.add(i);
            find(i + 1, selectedIdx, goodSoils);
            selectedIdx.remove(selectedIdx.size() - 1);
        }
    }

    private static void chooseGreenOrRed(int start, List<Integer> greenIdx, List<Integer> selectedIdx, List<int[]> goodSoils) {
        if (greenIdx.size() == G) {
            List<int[]> greenSoils = new ArrayList<>();
            List<int[]> redSoils = new ArrayList<>();

            for (int i = 0; i < selectedIdx.size(); i++) {
                int idx = selectedIdx.get(i);
                if (greenIdx.contains(idx)) {
                    greenSoils.add(goodSoils.get(idx));
                } else {
                    redSoils.add(goodSoils.get(idx));
                }
            }

            answer = Math.max(answer, BFS(greenSoils, redSoils));
            return;
        }

        for (int i = start; i < selectedIdx.size(); i++) {
            greenIdx.add(selectedIdx.get(i));
            chooseGreenOrRed(i + 1, greenIdx, selectedIdx, goodSoils);
            greenIdx.remove(greenIdx.size() - 1);
        }
    }

    private static int BFS(List<int[]> greenSoils, List<int[]> redSoils) {
        int[][] time = new int[N][M];
        int[][] color = new int[N][M];  // 0:빈칸, 1:녹, 2:빨, 3:꽃

        Queue<int[]> queue = new LinkedList<>();
        for (int[] greenSoil : greenSoils) {
            queue.offer(new int[]{greenSoil[0], greenSoil[1], 1, 0});
            color[greenSoil[0]][greenSoil[1]] = 1;
        }

        for (int[] redSoil : redSoils) {
            queue.offer(new int[]{redSoil[0], redSoil[1], 2, 0});
            color[redSoil[0]][redSoil[1]] = 2;
        }

        int flowers = 0;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int c = now[2];
            int t = now[3];

            if (color[x][y] == 3) continue;

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (map[nx][ny] == 0) continue;

                if (color[nx][ny] == 0) {
                    color[nx][ny] = c;
                    time[nx][ny] = t + 1;
                    queue.offer(new int[]{nx, ny, c, t + 1});
                } else if (color[nx][ny] == 1 || color[nx][ny] == 2) {
                    if (time[nx][ny] == t + 1 && color[nx][ny] != c) {
                        color[nx][ny] = 3;
                        flowers++;
                    }
                }
            }
        }

        return flowers;
    }
}
