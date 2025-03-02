package leetcode.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MinCost {

    public static void main(String[] args) {
//        int maxTime = 29;
//        int[][] edges = {{0,1,10},{1,2,10},{2,5,10},{0,3,1},{3,4,10},{4,5,15}};
//        int[] passingFees = {5,1,2,20,20,3};

        int maxTime = 30;
        int[][] edges = {
                {0, 1, 10}, {1, 2, 10}, {2, 3, 10}, {0, 3, 25}
        };
        int[] passingFees = {5, 1, 2, 10};

        System.out.println(minCost(maxTime, edges, passingFees));
    }

    public static int minCost(int maxTime, int[][] edges, int[] passingFees) {
        ArrayList<int[]>[] graph = new ArrayList[passingFees.length];

        for (int i = 0; i < passingFees.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int [] edge : edges) {
            graph[edge[0]].add(new int[] {edge[1], edge[2]});
            graph[edge[1]].add(new int[] {edge[0], edge[2]});
        }

        int[] minTime = new int[passingFees.length];
        Arrays.fill(minTime, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);    // 현재 위치, 시간, 통행료
        pq.add(new int[] {0, 0, passingFees[0]});
        minTime[0] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int nowCity = cur[0];
            int nowTime = cur[1];
            int nowFee = cur[2];

            if (nowCity == passingFees.length - 1) return nowFee;

            for (int[] next : graph[nowCity]) {
                int nextCity = next[0];
                int travelTime = next[1];

                if (nowTime + travelTime > maxTime) continue;
                if (nowTime + travelTime < minTime[nextCity]) {
                    minTime[nextCity] = nowTime + travelTime;
                    pq.add(new int[] {nextCity, nowTime + travelTime, nowFee + passingFees[nextCity]});
                }
            }
        }

        return -1;
    }
}
