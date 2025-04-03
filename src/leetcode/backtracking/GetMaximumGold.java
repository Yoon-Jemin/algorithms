package leetcode.backtracking;

public class GetMaximumGold {
    public static void main(String[] args) {
        int[][] grid = {
                {1,0,7},
                {2,0,6},
                {3,4,5},
                {0,3,0},
                {9,0,20}
        };
        System.out.println(getMaximumGold(grid));
    }

    public static int getMaximumGold(int[][] grid) {
        int max = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int[][] visited = new int[grid.length][grid[0].length];
                int count = DFS(i, j, grid, visited);
                max = Math.max(max,count);
            }
        }
        return max;
    }

    private static int DFS(int nowX, int nowY, int[][] grid, int[][] visited) {
        int count = grid[nowX][nowY];
        int[] moveX = {0, 0, -1, 1};
        int[] moveY = {-1, 1, 0, 0};
        visited[nowX][nowY] = 1;

        int max = 0;
        for (int i = 0; i < 4; i++) {
            int nextX = nowX + moveX[i];
            int nextY = nowY + moveY[i];
            if (nextX < 0 || nextY < 0 || nextX >= grid.length || nextY >= grid[0].length) continue;
            if (visited[nextX][nextY] == 0 && grid[nextX][nextY] != 0) {
                max = Math.max(max, DFS(nextX, nextY, grid, visited));
                visited[nextX][nextY] = 0;
            }
        }
        count += max;
        return count;
    }
}
