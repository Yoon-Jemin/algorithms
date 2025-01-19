package leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

public class CountGoodNodes {

    public static void main(String[] args) {
//        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}};
        int[][] edges = {{0,1}, {1,2}, {2,3}, {3,4}, {0,5}, {1,6}, {2,7}, {3,8}};
//        int[][] edges = {{0,1}, {1,2}, {1,3}, {1,4}, {0,5}, {5,6}, {6,7}, {7,8}, {0,9}, {9, 10}, {9, 12}, {10, 11}};
        System.out.println(countGoodNodes(edges));
    }

    public static int countGoodNodes(int[][] edges) {
        int n = edges.length + 1;
        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] result = DFS(0, -1, graph);
        return result[0];
    }

    public static int[] DFS(int now, int prev, ArrayList<Integer>[] graph) {
        if (graph[now].size() == 1 && prev != -1) {
            return new int[]{1, 1};
        }

        int goodNodeCount = 0;
        int totalChildNodes = 0;
        int childCount = -1;    // 첫 번째 자식의 서브트리 크기
        boolean isGoodNode = true;

        for (int next : graph[now]) {
            if (next == prev) continue;

            int[] childResult = DFS(next, now, graph);
            totalChildNodes += childResult[1];

            if (childCount == -1){
                childCount = childResult[1];
            } else if (childCount != childResult[1]){
                isGoodNode = false;
            }

            goodNodeCount += childResult[0];
        }

        if (isGoodNode) goodNodeCount++;

        return new int[]{goodNodeCount, totalChildNodes + 1};
    }
}

