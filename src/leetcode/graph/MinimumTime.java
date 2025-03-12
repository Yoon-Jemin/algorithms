package leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MinimumTime {

    public static void main(String[] args) {
//        int n = 5;
//        int[][] relation = {{1,5},{2,5},{3,5},{3,4},{4,5}};
//        int[] time = {1,2,3,4,5};

        int n = 9;
        int[][] relation = {{3,2},{3,1},{1,7},{2,7},{4,6},{2,9},{3,9},{4,9},{6,9},{8,9}};
        int[] time = {3,5,7,1,8,2,5,7,4};

        System.out.println(minimumTime(n, relation, time));
    }

    public static int minimumTime(int n, int[][] relations, int[] time) {
        int[] indegree = new int[n + 1];

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();

        for (int[] relation : relations) {
            graph[relation[0]].add(relation[1]);
            indegree[relation[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] totalTime = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                totalTime[i] = time[i - 1];
            }
        }

        int max = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            max = Math.max(max, totalTime[now]);

            for (int next : graph[now]) {
                indegree[next]--;
                totalTime[next] = Math.max(totalTime[next], totalTime[now] + time[next - 1]);
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return max;
    }
}
