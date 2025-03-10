package leetcode.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class FindMinArrowShots {

    public static void main(String[] args) {
//        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        int[][] points = {{9,12},{1,10},{4,11},{8,12},{3,9},{6,9},{6,7}};
        System.out.println(findMinArrowShots(points));
    }

    public static int findMinArrowShots(int[][] points) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            else return b[1] - a[1];
        });
        for (int[] point : points) pq.offer(point);

        int count = 0;
        while (!pq.isEmpty()) {
            count++;
            int[] now = pq.poll();
            int end = now[1];
            int limit = end;

            while (!pq.isEmpty() && pq.peek()[0] <= limit) {
                int[] next = pq.poll();
                int nextEnd = next[1];
                limit = Math.min(limit, nextEnd);
            }
        }

        return count;
    }
}
