package leetcode.dfs;

public class NumEnclaves {

    public static void main(String[] args) {
        int[][] grid = {
                {0,0,0,0},
                {1,0,1,0},
                {0,1,1,0},
                {0,0,0,0}
        };

        System.out.println(numEnclaves(grid));
    }

    public static class Result {
        boolean isEnclaved;
        int count;

        public Result(boolean isEnclaved, int count) {
            this.isEnclaved = isEnclaved;
            this.count = count;
        }
    }

    public static int numEnclaves(int[][] grid) {
        int maxX = grid.length;
        int maxY = grid[0].length;

        int[][] visited = new int[maxX][maxY];
        int count = 0;

        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (visited[i][j] == 0 && grid[i][j] == 1) {
                    Result result = DFS(i, j, grid, visited);
                    if (result.isEnclaved) count += result.count;
                }
            }
        }

        return count;
    }

    public static Result DFS(int x, int y, int[][] grid, int[][] visited) {
        Result result = new Result(true, 1);
        visited[x][y] = 1;
        if (x == 0 || y == 0 || x == grid.length - 1 || y == grid[0].length - 1) {
            result.isEnclaved = false;
        }

        int[] moveX = {1, -1, 0, 0};
        int[] moveY = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int newX = x + moveX[i];
            int newY = y + moveY[i];
            if (newX < 0 || newY < 0 || newX > grid.length - 1 || newY > grid[0].length - 1) continue;
            if (visited[newX][newY] == 0 && grid[newX][newY] == 1) {
                Result newResult = DFS(newX, newY, grid, visited);
                if (newResult.isEnclaved) result.count += newResult.count;
                else result.isEnclaved = false;
            }
        }

        return result;
    }
}
