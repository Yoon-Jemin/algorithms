package leetcode.dfs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumEffortPath {

    public static void main(String[] args) {
        int[][] heights = {
                {1,2,2},{3,8,2},{5,3,5}
        };

        System.out.println(minimumEffortPath(heights));
    }

    public static int minimumEffortPath(int[][] heights) {
        int[] moveX = {0,0,1,-1};
        int[] moveY = {1,-1,0,0};
        int answer = Integer.MAX_VALUE;

        Boolean[][] visited =new Boolean[heights.length][heights[0].length];
        for (Boolean[] v : visited) Arrays.fill(v, false);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(o -> o[2]));
        pq.offer(new int[]{0,0,0});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int nowX = cur[0];
            int nowY = cur[1];
            int maxHeightDiff = cur[2];
            visited[nowX][nowY] = true;

            if (nowX == heights.length - 1 && nowY == heights[0].length - 1) {
                answer = Math.min(maxHeightDiff, answer);
                visited[nowX][nowY] = false;
            }

            for (int i = 0; i < moveX.length; i++) {
                int nextX = nowX + moveX[i];
                int nextY = nowY + moveY[i];
                if (nextX < 0 || nextX >= heights.length || nextY < 0 || nextY >= heights[0].length) continue;
                if (visited[nextX][nextY]) continue;

                int nextMaxHeightDiff = Math.max(maxHeightDiff, Math.abs(heights[nextX][nextY] - heights[nowX][nowY]));
                pq.offer(new int[]{nextX, nextY, nextMaxHeightDiff});
            }
        }

        return answer;
    }

}
