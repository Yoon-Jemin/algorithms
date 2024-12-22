package leetcode.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SurroundedRegions {

    public static void main(String[] args) {
        char[][] board = {
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };
    }

    static int[] move_X = {1, -1, 0, 0};
    static int[] move_Y = {0, 0, 1, -1};
    public static void solve(char[][] board) {

        int max_X = board[0].length;
        int max_Y = board.length;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') continue;
                Boolean[][] visited = new Boolean[max_X][max_Y];
                List<int[]> list = new ArrayList<>();
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(new int[]{i, j});

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    list.add(now);
                    visited[now[0]][now[1]] = true;

                    for (int k = 0; k < 4; k++) {
                        int nx = now[0] + move_X[k];
                        int ny = now[1] + move_Y[k];
                        if (visited[nx][ny]) continue;

                    }
                }

                if ()


            }
        }
    }
}
