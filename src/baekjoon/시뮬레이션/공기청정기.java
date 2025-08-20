package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 공기청정기 {

    static int N, M, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        List<int[]> purifier = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    purifier.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < K; i++) {
            int[][] spreadMap = spread(map);

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    map[j][k] += spreadMap[j][k];
                }
            }

            purify(purifier, map);
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer += map[i][j];
            }
        }

        System.out.println(answer + 2);
    }

    private static int[][] spread(int[][] map) {
        int[][] spreadMap = new int[N][M];

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    int initial = map[i][j];
                    int spreadTotal = 0;
                    int spread = initial / 5;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        if (map[nx][ny] != -1) {
                            spreadMap[nx][ny] += spread;
                            spreadTotal += spread;
                        }
                    }

                    spreadMap[i][j] += spreadTotal * -1;
                }
            }
        }

        return spreadMap;
    }

    private static void purify(List<int[]> purifier, int[][] map) {
        int top = purifier.get(0)[0];
        int bottom = purifier.get(1)[0];

        // 위쪽 반시계
        for (int i = top - 1; i > 0; i--) map[i][0] = map[i - 1][0];
        for (int j = 0; j < M - 1; j++) map[0][j] = map[0][j + 1];
        for (int i = 0; i < top; i++) map[i][M - 1] = map[i + 1][M - 1];
        for (int j = M - 1; j > 1; j--) map[top][j] = map[top][j - 1];
        map[top][1] = 0;

        // 아래쪽 반시계
        for (int i = bottom + 1; i < N - 1; i++) map[i][0] = map[i + 1][0];
        for (int j = 0; j < M - 1; j++) map[N - 1][j] = map[N - 1][j + 1];
        for (int i = N - 1; i > bottom; i--) map[i][M - 1] = map[i - 1][M - 1];
        for (int j = M - 1; j > 1; j--) map[bottom][j] = map[bottom][j - 1];
        map[bottom][1] = 0;
    }
}
