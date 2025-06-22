package leetcode.graph;

public class GameOfLife {

    public static void main(String[] args) {

    }

    static int[] dx1 = {-1, 1, 0, 0};
    static int[] dy1 = {0, 0, -1, 1};
    static int[] dx2 = {1, 1, -1, -1};
    static int[] dy2 = {1, -1, 1, -1};
    public static void gameOfLife(int[][] board) {
        int[][] answer = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    if (checkLive(i, j, board)) answer[i][j] = 1;
                    else answer[i][j] = 0;
                } else {
                    if (checkDead(i, j, board)) answer[i][j] = 1;
                    else answer[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = answer[i][j];
            }
        }
    }

    private static boolean checkLive(int i, int j, int[][] board) {
        int numLiveNeighbors = 0;

        for (int k = 0; k < 4; k++) {
            int nx = i + dx1[k];
            int ny = j + dy1[k];

            if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length) continue;
            if (board[nx][ny] == 1) numLiveNeighbors++;
        }

        for (int k = 0; k < 4; k++) {
            int nx = i + dx2[k];
            int ny = j + dy2[k];
            if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length) continue;
            if (board[nx][ny] == 1) numLiveNeighbors++;
        }

        if (numLiveNeighbors == 2 || numLiveNeighbors == 3) return true;
        return false;
    }

    private static boolean checkDead(int i, int j, int[][] board) {
        int numLiveNeighbors = 0;

        for (int k = 0; k < 4; k++) {
            int nx = i + dx1[k];
            int ny = j + dy1[k];

            if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length) continue;
            if (board[nx][ny] == 1) numLiveNeighbors++;
        }

        for (int k = 0; k < 4; k++) {
            int nx = i + dx2[k];
            int ny = j + dy2[k];
            if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length) continue;
            if (board[nx][ny] == 1) numLiveNeighbors++;
        }

        if (numLiveNeighbors == 3) return true;
        return false;
    }
}
