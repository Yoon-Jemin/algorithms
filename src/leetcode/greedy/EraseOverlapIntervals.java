package leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class EraseOverlapIntervals {

    public static void main(String[] args) {
//        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int[][] intervals = {{1,100}, {11,22}, {1,11}, {2,12}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));// 끝을 기준으로 오름차순
        for (int[] interval : intervals) pq.offer(interval);

        int count = 0;
        int limit = pq.poll()[1];
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek()[0] < limit) {
                pq.poll();
                count++;
            }
            if (!pq.isEmpty()) limit = pq.poll()[1];
        }

        return count;
    }
}
