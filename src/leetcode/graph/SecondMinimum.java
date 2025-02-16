package leetcode.graph;

import java.util.*;

public class SecondMinimum {

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{1,2}, {1,3}, {1,4}, {3,4}, {4,5}};
        int time = 3;
        int change = 5;

        System.out.println(secondMinimum(n, edges, time, change));
    }

    public static int secondMinimum(int n, int[][] edges, int time, int change) {
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Queue<int[]> queue = new LinkedList<>();
        int[] visitedNum = new int[n + 1];
        int[] timeArr = new int[n + 1];
        Arrays.fill(timeArr, -1);
        queue.offer(new int[] {1, 0});  // 현재 위치, 총 시간
        timeArr[1] = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();

                int nowNode = cur[0];
                int nowTime = cur[1];

                int nextTime = 0;
                int nowLight = nowTime / change;
                if (nowLight % 2 == 0) nextTime = nowTime + time;
                else nextTime = (nowLight + 1) * change + time;

                for (int nextNode : graph[nowNode]) {
                    if (visitedNum[nextNode] < 2 && timeArr[nextNode] < nextTime) {
                        queue.offer(new int[] {nextNode, nextTime});
                        visitedNum[nextNode]++;
                        timeArr[nextNode] = nextTime;
                        if (nextNode == n && visitedNum[nextNode] == 2) return nextTime;
                    }
                }
            }
        }

        return -1;
    }
}
