package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 체스판다시칠하기 {

    static int N, M;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == 'B') map[i][j] = 0;
                else map[i][j] = 1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i + 8 <= N && j + 8 <= M) {
                    answer = Math.min(answer, check(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    private static int check(int i, int j) {
        // 첫 번째가 0을 기대할 때,
        int repaint1 = 0;
        int repaint2 = 0;

        for (int x = i; x < i + 8; x++) {
            for (int y = j; y < j + 8; y++) {
                int expected = (x + y) % 2;

                if (map[x][y] != expected) repaint1++;
                if (map[x][y] != 1 - expected) repaint2++;
            }
        }

        return Math.min(repaint1, repaint2);
    }
}
