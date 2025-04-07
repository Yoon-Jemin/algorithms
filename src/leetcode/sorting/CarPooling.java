package leetcode.sorting;

import java.util.PriorityQueue;

public class CarPooling {

    public static void main(String[] args) {
//        int[][] trips = {{2,1,5},{3,3,7}};
        int[][] trips = {{1,1,5},{1,3,5},{1,4,8},{1,7,10}};
//        int capacity = 5;
        int capacity = 10;
        System.out.println(carPooling(trips, capacity));
    }

    public static boolean carPooling(int[][] trips, int capacity) {
        PriorityQueue<int[]> pq1 = new PriorityQueue<>((a,b) -> a[1] - b[1]); // 시작 기준 오름차순
        for (int[] trip : trips) {
            pq1.offer(trip);
        }

        int people = 0;
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((a,b) -> a[2] - b[2]);   // 끝 기준 오름 차순
        while (!pq1.isEmpty()) {
            int[] trip = pq1.poll();
            int start = trip[1];
            int end = trip[2];
            int passenger = trip[0];

            people += passenger;

            while (!pq2.isEmpty()) {
                int[] trip2 = pq2.peek();
                if (trip2[2] <= start) {
                    people -= trip2[0];
                    pq2.poll();
                } else {
                    break;
                }
            }
            pq2.add(trip);
            if (people > capacity) return false;
        }

        return true;
    }
}
