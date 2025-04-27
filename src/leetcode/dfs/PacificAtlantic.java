package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class PacificAtlantic {

    public static void main(String[] args) {
//        int[][] heights = {
//                {1,2,2,3,5},
//                {3,2,3,4,4},
//                {2,4,5,3,1},
//                {6,7,1,4,5},
//                {5,1,1,2,4}
//        };

        int[][] heights = {{2,1},{1,2}};

        System.out.println(pacificAtlantic(heights));
    }

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                if (isAnswer(i, j, heights)) {
                    res.add(List.of(i, j));
                }
            }
        }

        return res;
    }

    public static class Check {
        boolean atlantic = false;
        boolean pacific = false;
    }

    private static boolean isAnswer(int x, int y, int[][] heights) {
        int[][] visited = new int[heights.length][heights[0].length];
        Check check = dfs(x, y, heights, visited);
        return check.pacific == true && check.atlantic == true;
    }

    private static Check dfs(int x, int y, int[][] heights, int[][] visited) {
        int[] move_x = {0, 0, -1, 1};
        int[] move_y = {1, -1, 0, 0};

        Check check = new Check();
        visited[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + move_x[i];
            int ny = y + move_y[i];

            if (nx < 0 || ny < 0) {
                check.pacific = true;
                continue;
            } else if (nx >= heights.length || ny >= heights[0].length) {
                check.atlantic = true;
                continue;
            }

            if (visited[nx][ny] == 0 && heights[nx][ny] <= heights[x][y]) {
                Check nextCheck = dfs(nx, ny, heights, visited);
                if (nextCheck.pacific == true) check.pacific = true;
                if (nextCheck.atlantic == true) check.atlantic = true;
            }
        }

        return check;
    }
}
