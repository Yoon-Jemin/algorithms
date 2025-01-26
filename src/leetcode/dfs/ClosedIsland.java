package leetcode.dfs;

import java.util.Arrays;

public class ClosedIsland {

    public static void main(String[] args) {
        int[][] gird ={
                {0,1,0,1,0,0,0,1,1,0,1,1,0,0,1,1,1,0,1,1},
                {0,1,1,0,0,0,0,1,1,1,0,1,0,1,1,0,0,1,0,1},
                {1,1,0,1,0,0,1,1,1,0,0,0,1,0,0,1,0,1,0,0},
                {0,1,1,1,0,0,0,0,0,0,1,1,1,0,0,0,1,1,1,0},
                {1,1,0,0,1,0,0,1,1,0,1,1,0,1,1,1,0,0,1,1},
                {1,1,0,0,0,0,0,1,0,1,1,1,0,1,0,0,0,0,0,1},
                {1,0,1,1,0,1,0,1,0,0,1,0,1,1,1,1,1,0,1,0},
                {1,0,0,1,1,0,0,1,0,1,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,1,1,1,0,1,1,1,0,1,0,1,1,0,1,0,0,0},
                {1,1,0,0,0,0,1,1,0,0,0,1,0,0,1,0,1,0,1,1},
                {1,0,0,1,1,1,1,0,1,0,1,1,1,0,0,0,0,1,1,0},
                {1,1,0,0,0,0,0,0,1,1,1,1,0,1,0,1,0,1,1,1},
                {0,1,1,0,0,1,1,0,0,1,0,1,1,1,1,1,1,0,0,0},
                {1,0,0,0,1,1,0,1,1,1,0,0,1,0,1,1,0,1,0,1}
        };

        System.out.println(closedIsland(gird));
    }

    public static int closedIsland(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            if (i == 0 || i == grid.length - 1) {   // 양끝
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) continue;
                    DFS1(i, j, grid);
                }
            } else {
                if (grid[i][0] == 0) DFS1(i, 0, grid);
                if (grid[i][grid[0].length - 1] == 0) DFS1(i, grid[0].length - 1, grid);
            }
        }

        int answer = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    DFS1(i, j, grid);
                    answer++;
                }
            }
        }

        return answer;
    }

    public static void DFS1(int now_x, int now_y, int[][] grid) {
        int[] direction_X = {0, 0, 1, -1};
        int[] direction_Y = {1, -1, 0, 0};

        grid[now_x][now_y] = 1;
        for (int k = 0; k < 4; k++) {
            int next_x = now_x + direction_X[k];
            int next_y = now_y + direction_Y[k];

            if (next_x < 0 || next_y < 0 || next_x >= grid.length || next_y >= grid[0].length) continue;
            if (grid[next_x][next_y] == 0) DFS1(next_x, next_y, grid);
        }
    }
}
