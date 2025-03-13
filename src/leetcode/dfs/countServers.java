package leetcode.dfs;

public class countServers {

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,1,0,0},
                {0,0,1,0},
                {0,0,1,0},
                {0,0,0,1}
        };
        System.out.println(countServers(grid));
    }

    public static int countServers(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int connected = DFS(i, j, grid);
                    if (connected > 1) count += connected;
                }
            }
        }

        return count;
    }

    public static int DFS(int x, int y, int[][] grid) {
        int count = 1;
        grid[x][y] = 0;

        for (int i = 0; i < grid.length; i++) {
            if (grid[i][y] == 1) {
                count += DFS(i, y, grid);
            }
        }

        for (int i = 0; i < grid[0].length; i++) {
            if (grid[x][i] == 1) {
                count += DFS(x, i, grid);
            }
        }

        return count;
    }
}
