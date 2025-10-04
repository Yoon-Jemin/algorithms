package programmers.level_3;

public class 자물쇠와열쇠 {

    public static void main(String[] args) {
        int[][] key = {
                {0, 0, 0},
                {1, 0, 0},
                {0, 1, 1}
        };

        int[][] lock = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        System.out.println(solution(key, lock));
    }

    static int N, M;
    static boolean answer = false;
    public static boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;

        // 그대로
        simulate(key, lock);
        if (answer) return answer;

        // 90도 회전
        turn(key);
        simulate(key, lock);
        if (answer) return answer;

        // 90도 회전
        turn(key);
        simulate(key, lock);
        if (answer) return answer;

        // 90도 회전
        turn(key);
        simulate(key, lock);

        return answer;
    }

    private static void simulate(int[][] key, int[][] lock) {
        int size = N + 2 * (M - 1);
        int[][] map = new int[size][size];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i + M - 1][j + M - 1] = lock[i][j];
            }
        }

        for (int x = 0; x <= size - M; x++) {
            for (int y = 0; y <= size - M; y++) {
                coverMap(x, y, map, key);

                if (check(map)) {
                    answer = true;
                    return; // 바로 성공
                }

                uncoverMap(x, y, map, key);
            }
        }
    }

    private static void coverMap(int x, int y, int[][] map, int[][] key) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                map[x + i][y + j] += key[i][j];
            }
        }
    }

    private static boolean check(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i + M - 1][j + M - 1] != 1) return false;
            }
        }
        return true;
    }

    private static void uncoverMap(int x, int y, int[][] map, int[][] key) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                map[x + i][y + j] -= key[i][j];
            }
        }
    }

    private static void turn(int[][] key) {
        int[][] copyKey = new int[M][M];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                copyKey[i][j] = key[i][j];
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                key[j][M - 1 - i] = copyKey[i][j];
            }
        }
    }
}
