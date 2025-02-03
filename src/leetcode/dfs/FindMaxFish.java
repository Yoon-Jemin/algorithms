package leetcode.dfs;

public class FindMaxFish {

    public static void main(String[] args) {
        int[][] grid = {
                {0,2,1,0},
                {4,0,0,3},
                {1,0,0,4},
                {0,3,2,0}
        };
        System.out.println(findMaxFish(grid));
    }

    public static int findMaxFish(int[][] grid) {
        int answer = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    answer = Math.max(answer, DFS(i, j, grid));
                }
            }
        }

        return answer;
    }

    public static int DFS(int i, int j, int[][] grid) {
        int count = grid[i][j];
        grid[i][j] = 0;

        int[] moveX = {0,0,1,-1};
        int[] moveY = {1,-1,0,0};

        for (int k = 0; k < 4; k++) {
            int nextX = i + moveX[k];
            int nextY = j + moveY[k];
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length) continue;
            if (grid[nextX][nextY] != 0) count += DFS(nextX, nextY, grid);
        }

        return count;
    }
}
