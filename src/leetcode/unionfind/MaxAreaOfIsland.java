package leetcode.unionfind;

public class MaxAreaOfIsland {

    public static void main(String[] args) {
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };

        System.out.println(maxAreaOfIsland(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int max = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(max, DFS(i,j, grid));
                }
            }
        }

        return max;
    }

    public static int DFS(int i, int j, int[][] grid) {
        int[] direction_x = {0, 0, -1, 1};
        int[] direction_y = {1, -1, 0, 0};

        grid[i][j] = -1;
        int count = 1;
        for (int k = 0; k < 4; k++) {
            int next_x = i + direction_x[k];
            int next_y = j + direction_y[k];
            if (next_x < 0 || next_y <0 || next_x >= grid.length || next_y >= grid[0].length) continue;
            if (grid[next_x][next_y] == 1) {
                count += DFS(next_x, next_y, grid);
            }
        }

        return count;
    }
}
