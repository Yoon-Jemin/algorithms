package leetcode.dfs;

public class LongestIncreasingPath {

    public static void main(String[] args) {
        int[][] matrix = {
                {9,9,4},
                {6,6,8},
                {2,1,1}
        };

        System.out.println(longestIncreasingPath(matrix));
    }

    public static int[] moveX = {0, 0, 1, -1};
    public static int[] moveY = {1, -1, 0, 0};
    public static int longestIncreasingPath(int[][] matrix) {
        int answer = 0;

        int[][] dist = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                answer = Math.max(answer,DFS(i,j,matrix,dist));
            }
        }

        return answer;
    }

    public static int DFS(int i, int j, int[][] matrix, int[][] dist) {
       if (dist[i][j] != 0) return dist[i][j];

       int max = 0;
        for (int k = 0; k < 4; k++) {
            int nextX = i + moveX[k];
            int nextY = j + moveY[k];
            if (nextX < 0 || nextX >= matrix.length || nextY < 0 || nextY >= matrix[0].length) continue;
            if (matrix[nextX][nextY] > matrix[i][j]) {
                max = Math.max(max, DFS(nextX,nextY,matrix, dist));
            }
        }

        dist[i][j] = 1 + max;
        return dist[i][j];
    }

}
