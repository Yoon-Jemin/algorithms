package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커붙이기 {

    static int N, M, K;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 스티커의 각 칸은 상하좌우로 모두 연결되어 있음
        // 모눈종이의 크기는 스티커의 크기에 꼭 맞아서, 상하좌우에 스티커가 포함되지 않는 불필요한 행이나 열이 존재하지 않음

        // 가장 위쪽에 해당하는 위치도 여러 곳이 있다면 그중에서 가장 왼쪽의 위치를 선택

        // 만약 스티커를 붙일 수 있는 위치가 전혀 없어서 스티커를 붙이지 못했다면
        // 스티커를 시계 방향으로 90도 회전
        // 0도, 90도, 180도, 270도 회전시켜 봤음에도 스티커를 붙이지 못했다면 해당 스티커를 붙이지 않고 버린다

        N = Integer.parseInt(st.nextToken());   // 가로 길이
        M = Integer.parseInt(st.nextToken());   // 세로 길이
        K = Integer.parseInt(st.nextToken());   // 스티커의 개수

        map = new int[N][M];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[x][y];

            for (int i = 0; i < x; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < y; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int turn = 0; turn < 4; turn++) {
                if (check(sticker)) break;
                sticker = turn(sticker);
            }
        }

        count();
    }

    private static boolean check(int[][] sticker) {
        int x = sticker.length;
        int y = sticker[0].length;

        for (int i = 0; i <= N - x; i++) {
            for (int j = 0; j <= M - y; j++) {
                boolean isPossible = true;

                for (int n = 0; n < x; n++) {
                    for (int m = 0; m < y; m++) {
                        if (map[i + n][j + m] == 1 && sticker[n][m] == 1) {
                            isPossible = false;
                        }
                    }
                }

                if (isPossible) {
                    for (int n = 0; n < x; n++) {
                        for (int m = 0; m < y; m++) {
                            if (sticker[n][m] == 1) map[i + n][j + m] = 1;
                        }
                    }
                    return true;
                }

            }
        }

        return false;
    }

    private static int[][] turn (int[][] sticker) {
        int x = sticker.length;     // 2
        int y = sticker[0].length;      // 5

        int[][] newSticker = new int[y][x];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                newSticker[j][x - i - 1] = sticker[i][j];
            }
        }

        return newSticker;
    }

    private static void count() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) count++;
            }
        }

        System.out.println(count);
    }
}
