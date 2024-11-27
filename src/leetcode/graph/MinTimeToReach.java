package leetcode.graph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinTimeToReach {

    public static void main(String[] args) {
        int[][] moveTime = {{94,79,62,27,69,84}, {6,32,11,82,42,30}};
        System.out.println(minTimeToReach(moveTime));
    }

    static int[] move_X = {1, -1, 0, 0};
    static int[] move_Y = {0, 0, -1, 1};
    public static int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length;    // y
        int n = moveTime[0].length;     // x

        int[][] map = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[j][i] = Integer.MAX_VALUE;
            }
        }
        map[0][0] = 0;

        Queue<int[]> queue = new LinkedList<>();
//        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(a -> a[2]));
        queue.offer(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int now_x = now[0];
            int now_y = now[1];
            int curTime = now[2];

            for (int i = 0; i < 4; i++) {
                int next_x = now_x + move_X[i];
                int next_y = now_y + move_Y[i];
                if (next_x >= 0 && next_x < n && next_y >= 0 && next_y < m) {
                    int newTime = Math.max(moveTime[next_y][next_x], curTime) + 1;
                    if (map[next_y][next_x] > newTime) {
                        map[next_y][next_x] = newTime;
                        queue.offer(new int[]{next_x, next_y, newTime});
                    }
                }
            }
        }

        return map[m - 1][n - 1];
    }

}
