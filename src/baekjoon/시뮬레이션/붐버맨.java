package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 붐버맨 {

    static int N;
    static int M;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int seconds = Integer.parseInt(st.nextToken());
        int time = 0;

        List<int[]> bombs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < M; j++) map[i][j] = s.charAt(j);
        }

        if (seconds == 1) {
            print(map);
            return;
        }

        if (seconds % 2 == 0) {
            printFull();
            return;
        }

        char[][] firstExplode = explode(map);
        if (seconds % 4 == 3) {
            print(firstExplode);
        } else {
            print(explode(firstExplode));
        }
    }


    private static char[][] explode(char[][] before) {
        char[][] after = new char[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(after[i], 'O');
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (before[i][j] == 'O') {
                    after[i][j] = '.';
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        after[nx][ny] = '.';
                    }
                }
            }
        }

        return after;
    }

    private static void print(char[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void printFull() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append("O");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
