package leetcode.graph;

import java.util.*;

public class LongestCycle {

    public static void main(String[] args) {
        int[] edges = {4,3,3,4,7,2,3,3};
        System.out.println(longestCycle(edges));
    }

    public static int longestCycle(int[] edges) {
        int n = edges.length;
        int[] visitTimes = new int[n];
        Arrays.fill(visitTimes, -1);
        int time = 1;
        int answer = -1;

        for (int i = 0; i < n; i++) {
            if (visitTimes[i] > 0) continue;

            int node = i;
            int startTime = time;
            visitTimes[node] = time++;

            while (node != -1) {
                int next = edges[node];
                if (next == -1) break;

                if (visitTimes[next] != -1) { // 이미 방문한 노드
                    if (visitTimes[next] >= startTime) {
                        answer = Math.max(answer, time - visitTimes[next]);
                    }
                    break;
                }

                visitTimes[next] = time++;
                node = next;
            }
        }

        return answer;
    }
}
