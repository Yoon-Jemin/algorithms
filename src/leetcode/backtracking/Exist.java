package leetcode.backtracking;

public class Exist {

    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };

        String word = "SEE";

        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    int[][] visited = new int[board.length][board[0].length];
                    visited[i][j] = 1;
                    if(backTrack(i, j,0, word, board, visited)) return true;
                }
            }
        }

        return false;
    }

    public static boolean backTrack(int x, int y, int idx, String word, char[][] board, int[][] visited) {
        if (idx + 1 == word.length()) return true;

        char nextWord = word.charAt(idx + 1);
        int[] moveX = {-1, 1, 0, 0};
        int[] moveY = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];

            if (nextX < 0 || nextY < 0 || nextX >= board.length || nextY >= board[0].length) continue;
            if (visited[nextX][nextY] == 1) continue;
            if (board[nextX][nextY] == nextWord) {
                visited[nextX][nextY] = 1;
                if (backTrack(nextX, nextY, idx + 1, word, board, visited)) return true;
                visited[nextX][nextY] = 0;
            }
        }

        return false;
    }
}
