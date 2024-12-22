package leetcode.dfs;

import java.util.*;

public class SurroundedRegions {

    public static void main(String[] args) {
        char[][] board = {
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'},
                {'X','O','X','O','X','O'},
                {'O','X','O','X','O','X'}
        };

//        char[][] board = {
//                {'O','O'},
//                {'O','O'}
//        };

        solve(board);
    }

    public static void solve(char[][] board) {

        int max_X = board.length;
        int max_Y = board[0].length;

        for (int i = 0; i < max_X; i++) {
            if (board[i][0] == 'O') markSafe(board, i, 0);
            if (board[i][max_Y - 1] == 'O') markSafe(board, i, max_Y - 1);
        }

        for (int j = 0; j < max_Y; j++) {
            if (board[0][j] == 'O') markSafe(board, 0, j);
            if (board[max_X - 1][j] == 'O') markSafe(board, max_X - 1, j);
        }

        for (int i = 0; i < max_X; i++) {
            for (int j = 0; j < max_Y; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == 'S') board[i][j] = 'O';
            }
        }
    }

    private static void markSafe(char[][] board, int x, int y) {
        int[] move_x = {1, -1, 0, 0};
        int[] move_Y = {0, 0, 1, -1};

        int max_X = board.length;
        int max_Y = board[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int now_X = now[0];
            int now_Y = now[1];

            if (board[now_X][now_Y] != 'O') continue;
            board[now_X][now_Y] = 'S';

            for (int i = 0; i < 4; i++) {
                int next_X = now_X + move_x[i];
                int next_Y = now_Y + move_Y[i];

                if (next_X < 0 || next_Y < 0 || next_X >= max_X || next_Y >= max_Y) continue;
                queue.add(new int[]{next_X, next_Y});
            }
        }
    }
}
