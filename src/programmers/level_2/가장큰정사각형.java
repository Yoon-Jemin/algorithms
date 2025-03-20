package programmers.level_2;

public class 가장큰정사각형 {
    public int solution(int [][]board) {
        int row = board.length;
        int col = board[0].length;
        int maxSide = 0;

        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++){
            dp[i][0] = board[i][0];
            maxSide = Math.max(maxSide, dp[i][0]);
        }

        for (int i = 0; i < col; i++){
            dp[0][i] = board[0][i];
            maxSide = Math.max(maxSide, dp[0][i]);
        }

        for (int i = 1; i < row; i++){
            for (int j = 1; j < col; j++){
                if (board[i][j] != 1) continue;
                dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }

        return maxSide * maxSide;
    }
}
