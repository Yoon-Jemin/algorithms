package programmers.level_3;

public class 보행자천국 {

    public static void main(String[] args) {
        int m = 3;
        int n = 6;
        int[][] city_map = {
                {0, 2, 0, 0, 0, 2},
                {0, 0, 2, 0, 1, 0},
                {1, 0, 0, 2, 2, 0}
        };

//        int m = 3;
//        int n = 3;
//        int[][] city_map = {
//                {0, 0, 0},
//                {0, 0, 0},
//                {0, 0, 0}
//        };

        System.out.println(solution(m, n, city_map));
    }

    static int MOD = 20170805;
    public static int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];    // 0: 위쪽에 옴, 1: 왼쪽에서 옴

        dp[0][0][1] = 0;
        dp[0][0][0] = 0;

        for (int i = 1; i < m; i++) {
            if (cityMap[i][0] == 1) break;
            dp[i][0][0] = 1;
        }

        for (int j = 1; j < n; j++) {
            if (cityMap[0][j] == 1) break;
            dp[0][j][1] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (cityMap[i][j] == 1) continue;   // 자동차가 지나갈 수 없음

                if (cityMap[i - 1][j] == 2) {
                    dp[i][j][0] = dp[i - 1][j][0] % MOD;
                } else {
                    dp[i][j][0] = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                }

                if (cityMap[i][j - 1] == 2) {
                    dp[i][j][1] = dp[i][j - 1][1] % MOD;
                } else {
                    dp[i][j][1] = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                }
            }
        }

        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}
