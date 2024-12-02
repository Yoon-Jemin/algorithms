package programmers.level_3;

public class 최적의행렬곱셈 {

    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{5,3},{3,10},{10,6}}));
    }

    public static int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
        int[][] dp = new int[n][n];

        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++){
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++){
                    int cost = dp[i][k] + dp[k + 1][j] + matrix_sizes[i][0] * matrix_sizes[k][1] * matrix_sizes[j][1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][n - 1];
    }
}
