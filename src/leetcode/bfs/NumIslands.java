package leetcode.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class NumIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','1'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };
        System.out.println(numIslands(grid));
    }

    static int[] move_X = {1, -1, 0, 0};
    static int[] move_Y = {0, 0, -1, 1};
    public static int numIslands(char[][] grid) {
        int max_X = grid.length;
        int max_Y = grid[0].length;
        int count = 0;


        for (int i = 0; i < max_X; i++) {
            for (int j = 0; j < max_Y; j++) {
                if (grid[i][j] == '0' || grid[i][j] == '2') continue;
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i, j});
                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    int now_X = now[0];
                    int now_Y = now[1];
                    for (int k = 0; k < 4; k++) {
                        int next_X = now_X + move_X[k];
                        int next_Y = now_Y + move_Y[k];
                        if (next_X < 0 || next_Y < 0 || next_X >= max_X || next_Y >= max_Y) continue;
                        if (grid[next_X][next_Y] == '1') {
                            grid[next_X][next_Y] = '2';
                            queue.add(new int[]{next_X, next_Y});
                        }
                    }
                }
                count++;
            }
        }
        return count;
    }


}
