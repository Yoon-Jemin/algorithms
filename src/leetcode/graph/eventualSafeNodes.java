package leetcode.graph;

import java.util.*;

public class eventualSafeNodes {

    public static void main(String[] args) {
//        int[][] graph = {{1,2,3,4}, {1,2}, {3,4}, {0,4}, {}};
        int[][] graph = {{1,2}, {2,3}, {5}, {0}, {5}, {}, {}};
        System.out.println(eventualSafeNodes(graph));
    }

    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        Boolean[] isSafe = new Boolean[n];

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(isSafeNode(i, graph, isSafe)) res.add(i);
        }

        return res;
    }

    private static boolean isSafeNode(int now, int[][] graph, Boolean[] isSafe) {
        if (isSafe[now] != null) return isSafe[now];

        isSafe[now] = false;
        for (int neighbor : graph[now]) {
            if (!isSafeNode(neighbor, graph, isSafe)) return false;
        }

        return isSafe[now] = true;
    }
}
