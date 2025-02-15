package leetcode.dfs;

public class CheckValidGrid {

    public static void main(String[] args) {
        int[][] grid = {
                {0,11,16,5,20},
                {17,4,19,10,15},
                {12,1,8,21,6},
                {3,18,23,14,9},
                {24,13,2,7,22}
        };

        System.out.println(checkValidGrid(grid));
    }

    public static boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) return false;
        return DFS(0, 0, 0, grid);
    }

    public static boolean DFS(int x, int y, int index, int[][] grid) {
        if (index == grid.length * grid.length - 1) return true;

        if (isValidGrid(x - 2, y - 1, grid)) {
            if (grid[x - 2][y - 1] == index + 1) return DFS(x - 2, y - 1, index + 1, grid);
        }
        if (isValidGrid(x - 2, y + 1, grid)) {
            if (grid[x - 2][y + 1] == index + 1) return DFS(x - 2, y + 1, index + 1, grid);
        }
        if (isValidGrid(x + 2, y - 1, grid)) {
            if (grid[x + 2][y - 1] == index + 1) return DFS(x + 2, y - 1, index + 1, grid);
        }
        if (isValidGrid(x + 2, y + 1, grid)) {
            if (grid[x + 2][y + 1] == index + 1) return DFS(x + 2, y + 1, index + 1, grid);
        }
        if (isValidGrid(x + 1, y - 2, grid)) {
            if (grid[x + 1][y - 2] == index + 1) return DFS(x + 1, y - 2, index + 1, grid);
        }
        if (isValidGrid(x - 1, y - 2, grid)) {
            if (grid[x - 1][y - 2] == index + 1) return DFS(x - 1, y - 2, index + 1, grid);
        }
        if (isValidGrid(x + 1, y + 2, grid)) {
            if (grid[x + 1][y + 2] == index + 1) return DFS(x + 1, y + 2, index + 1, grid);
        }
        if (isValidGrid(x - 1, y + 2, grid)) {
            if (grid[x - 1][y + 2] == index + 1) return DFS(x - 1, y + 2, index + 1, grid);
        }
        return false;
    }

    public static boolean isValidGrid(int x, int y, int[][] grid) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) return false;
        return true;
    }
}
