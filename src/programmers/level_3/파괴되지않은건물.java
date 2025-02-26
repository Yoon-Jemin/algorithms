package programmers.level_3;

public class 파괴되지않은건물 {

    public static void main(String[] args) {
        int[][] board = {
                {5,5,5,5,5},
                {5,5,5,5,5},
                {5,5,5,5,5},
                {5,5,5,5,5}
        };

        int[][] skill = {
                {1,0,0,3,4,4},
                {1,2,0,2,3,2},
                {2,1,0,3,1,2},
                {1,0,1,3,3,1}
        };

        System.out.println(solution(board, skill));
    }

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int n = board.length;
        int m = board[0].length;

        int[][] sum = new int[n + 1][m + 1];

        for (int[] s : skill) {
            int type = s[0];
            int degree = s[5];
            int a = s[1];
            int b = s[2];
            int c = s[3];
            int d = s[4];

            if (type == 1) {
                sum[a][b] -= degree;
                sum[a][d+1] += degree;
                sum[c+1][b] += degree;
                sum[c+1][d+1] -= degree;
            } else if (type == 2) {
                sum[a][b] += degree;
                sum[a][d+1] -= degree;
                sum[c+1][b] -= degree;
                sum[c+1][d+1] += degree;
            }
        }

        for (int i = 0 ; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }

        for (int j = 0; j < board[0].length; j++) {
            for (int i = 1; i < board.length; i++) {
                sum[i][j] += sum[i -1][j];
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += sum[i][j];
                if (board[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}
