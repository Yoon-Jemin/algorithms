package leetcode.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinTimeToReach2 {

    public static void main(String[] args) {
        int[][] moveTime = {{0,0,0,0}, {0,0,0,0}};
        System.out.println(minTimeToReach(moveTime));
    }

    static int[] move_X = {1, -1, 0, 0};
    static int[] move_Y = {0, 0, -1, 1};
    public static int minTimeToReach(int[][] moveTime) {

        int max_X = moveTime[0].length;
        int max_Y = moveTime.length;

        int[][] map = new int[max_Y][max_X];

        for (int i = 0; i < max_Y; i++) {
            for (int j = 0; j < max_X; j++) {
                map[i][j] = Integer.MAX_VALUE;
            }
        }
        map[0][0] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        queue.offer(new int[]{0, 0, 0, 1});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int now_X = now[0];
            int now_Y = now[1];
            int cur_time = now[2];
            int move_time = now[3];

            if (now_X == max_X-1 && now_Y == max_Y-1){
                return cur_time;
            }

            for (int i = 0; i < move_X.length; i++) {
                int next_X = now_X + move_X[i];
                int next_Y = now_Y + move_Y[i];
                if (next_X >= 0 && next_Y >= 0 && next_X < max_X && next_Y < max_Y) {
                    int next_time = Math.max(cur_time, moveTime[next_Y][next_X]) + move_time;
                    if (map[next_Y][next_X] > next_time) {
                        map[next_Y][next_X] = next_time;
                        if (move_time == 1) queue.offer(new int[]{next_X, next_Y, next_time, 2});
                        else queue.offer(new int[]{next_X, next_Y, next_time, 1});
                    }
                }
            }
        }

        return -1;
    }
}
