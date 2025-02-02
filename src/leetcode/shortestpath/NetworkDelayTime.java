package leetcode.shortestpath;

import java.util.*;

public class NetworkDelayTime {

    public static void main(String[] args) {
        int[][] times = {
                {2,1,1},
                {2,3,1},
                {3,4,1}
        };
        int n = 4;
        int k = 2;
        System.out.println(networkDelayTime(times,n,k));
    }

    public static int networkDelayTime(int[][] times, int n, int k) {
        Set<Integer> set = new HashSet<>();
        ArrayList<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] time : times) {
            graph[time[0]].add(new int[]{time[1], time[2]});
        }

        int[] time = new int[n + 1];
        Arrays.fill(time, Integer.MAX_VALUE);
        set.add(k);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{k, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int nowNode = poll[0];
            int nowDistance = poll[1];

            set.add(nowNode);
            if (nowDistance < time[nowNode]) time[nowNode] = nowDistance;

            for (int[] next : graph[nowNode]) {
                if (nowDistance + next[1] < time[next[0]]) queue.add(new int[]{next[0], nowDistance + next[1]});
            }
        }

        if (set.size() != n) return -1;

        int answer = 0;
        for (int i = 1; i < time.length; i++) {
            if (i == k) continue;
            answer = Math.max(answer, time[i]);
        }
        return answer;
    }
}
