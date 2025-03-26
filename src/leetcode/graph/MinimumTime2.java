package leetcode.graph;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumTime2 {

    public static void main(String[] args) {
        int[][] grid = {
                {0,1,3,2},
                {5,1,2,5},
                {4,3,8,6}
        };
        System.out.println(minimumTime(grid));
    }

    public static int minimumTime(int[][] grid) {
        int[] moveX = {0, 0, -1, 1};
        int[] moveY = {1, -1, 0, 0};

        int maxX = grid.length - 1;
        int maxY = grid[0].length - 1;

        if (grid[0][1] > 1 && grid[1][0] > 1) return -1;

        Boolean[][] visited = new Boolean[grid.length][grid[0].length];
        for (Boolean[] v : visited) {
            Arrays.fill(v, false);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));
        pq.add(new int[] {0, 0, 0});
        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int nowX = now[0], nowY = now[1], nowTime = now[2];
            if (nowX == maxX && nowY == maxY) return nowTime;

            if (visited[nowX][nowY]) continue;
            visited[nowX][nowY] = true;

            for (int i = 0; i < moveX.length; i++) {
                int nextX = nowX + moveX[i];
                int nextY = nowY + moveY[i];
                int nextTime = nowTime + 1;

                if (nextX < 0 || nextY < 0 || nextX > maxX || nextY > maxY) continue;
                if (visited[nextX][nextY]) continue;

                if (grid[nextX][nextY] > nextTime) {
                    int diff = grid[nextX][nextY] - nextTime;
                    if (diff % 2 == 1) nextTime += diff + 1;  // 2의 배수가 되도록 조정
                    else nextTime += diff;
                }
                pq.add(new int[] {nextX, nextY, nextTime});
            }
        }

        return -1;
    }
}
