package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumInvitations {

    public static void main(String[] args) {
        int[] favorite = {3,0,1,4,1};
        System.out.println(maximumInvitations(favorite));
    }

    public static int maximumInvitations(int[] favorite) {
        int n = favorite.length;    // 5
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            graph[i].add(favorite[i]);
            indegree[favorite[i]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] maxChain = new int[n];
        Arrays.fill(maxChain, 1);
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int fav = favorite[cur];
            maxChain[fav] = Math.max(maxChain[fav], maxChain[cur] + 1);
            indegree[fav]--;
            if (indegree[fav] == 0) queue.add(fav);
        }

        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        int maxCycleLength = 0;
        int maxTwoCycleLength = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && indegree[i] > 0) {
                int cycleSize = 0;
                int node = i;
                while (!visited[node]) {
                    visited[node] = true;
                    cycleSize++;
                    node = favorite[node];
                }

                if (cycleSize == 2) {
                    maxTwoCycleLength += maxChain[i] + maxChain[favorite[i]];
                } else {
                    maxCycleLength = Math.max(maxCycleLength, cycleSize);
                }
            }
        }

        return Math.max(maxCycleLength, maxTwoCycleLength);
    }
}
