package leetcode.graph;

import java.util.*;

public class MostProfitablePath {

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}, {3, 4}};
        int bob = 3;
        int[] amount = {-2, 4, 2, -4, 6};
//        int[][] edges = {{0, 1}};
//        int bob = 1;
//        int[] amount = {-7280, 2350};
//        int[][] edges = {{0, 2}, {0, 6}, {1, 3}, {1, 5}, {2, 5}, {4, 6}};
//        int bob = 6;
//        int[] amount = {8838,-6396,-5940,2694,-1366,4616,2966};
        System.out.println(mostProfitablePath(edges, bob, amount));
    }

    static List<List<Integer>> graph;
    static Map<Integer, Integer> bobTiming;
    static int n;
    public static int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        n = amount.length;
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        bobTiming = new HashMap<>();
        findBobPath(bob, -1, 0, new ArrayList<>());
        return dfs(0, -1, 0, amount);
    }

    private static boolean findBobPath(int node, int parent, int time, List<Integer> path) {
        if (node == 0) {
            path.add(node);
            for (int i = 0; i < path.size() - 1; i++) {
                bobTiming.put(path.get(i), i);
            }
            return true;
        }

        path.add(node);
        for (int next: graph.get(node)) {
            if (next == parent) continue;
            if (findBobPath(next, node, time + 1, path)) return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    private static int dfs(int node, int parent, int time, int[] amount) {
        int reward = calculateReward(node, time, amount);

        if (graph.get(node).size() == 1 && parent != -1) return reward;

        int maxIncome = Integer.MIN_VALUE;
        for (int next: graph.get(node)) {
            if (next == parent) continue;
            maxIncome = Math.max(maxIncome, dfs(next, node, time + 1, amount));
        }

        return reward + (maxIncome == Integer.MAX_VALUE ? 0 : maxIncome);
    }

    private static int calculateReward(int node, int aliceTime, int[] amount) {
        if (!bobTiming.containsKey(node)) return amount[node];

        int bobTime = bobTiming.get(node);

        if (aliceTime < bobTime) return amount[node];
        else if (aliceTime == bobTime) return amount[node] / 2;
        else return 0;
    }
}
