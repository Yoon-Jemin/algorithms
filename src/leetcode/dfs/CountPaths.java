package leetcode.dfs;

public class CountPaths {

    public static void main(String[] args) {
        int[][] grid = {{1,1}, {3,4}};
        System.out.println(countPaths(grid));
    }

    public static int[] move_X = {0,0,1,-1};
    public static int[] move_Y = {1,-1,0,0};
    private static final int MOD = 1_000_000_007;
    public static int countPaths(int[][] grid) {
        int count = 0;

        int[][] dp = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                count = (count + DFS(i, j, grid, dp)) % MOD;
            }
        }

        return count;
    }

    public static int DFS(int x, int y, int[][] grid, int[][] dp) {
        if (dp[x][y] != 0) return dp[x][y];

        int count = 1;
        for (int k = 0; k < 4; k++) {
            int next_X = x + move_X[k];
            int next_Y = y + move_Y[k];
            if (next_X < 0 || next_Y < 0 || next_X >= grid.length || next_Y >= grid[0].length) continue;
            if (grid[next_X][next_Y] > grid[x][y]) {
                count = (count + DFS(next_X, next_Y, grid, dp)) % MOD;
            }
        }

        dp[x][y] = count;
        return count;
    }
}
