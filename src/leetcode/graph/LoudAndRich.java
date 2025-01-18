package leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LoudAndRich {

    public static void main(String[] args) {
        int[][] richer = {{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}};
        int[] quiet = {3,2,5,4,6,1,7,0};
        int[] result = loudAndRich(richer, quiet);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        ArrayList<Integer>[] graph = new ArrayList[quiet.length];
        int[] indegree = new int[quiet.length];
        int[] answer = new int[quiet.length];

        for (int i = 0; i < quiet.length; i++) {
            graph[i] = new ArrayList<>();
            answer[i] = i;
        }

        for (int[] r : richer) {
            graph[r[0]].add(r[1]);
            indegree[r[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < quiet.length; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                if (quiet[answer[cur]] < quiet[answer[next]]) {
                    answer[next] = answer[cur];
                }

                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        return answer;
    }
}
