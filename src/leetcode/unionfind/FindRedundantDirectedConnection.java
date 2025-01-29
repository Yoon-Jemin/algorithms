package leetcode.unionfind;

import java.util.Arrays;

public class FindRedundantDirectedConnection {

    public static void main(String[] args) {
        int[][] edges = {{1,2}, {2,3}, {3,4}, {4,1}, {1,5}};
        int[] result = findRedundantDirectedConnection(edges);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] firstEdge = null;
        int[] secondEdge = null;

        Arrays.fill(parent, -1);
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            if (parent[node2] != -1) {  // 중복 간선 발생
                firstEdge = new int[]{parent[node2], node2};
                secondEdge = edge;
                break;
            }
            parent[node2] = node1;
        }

        int[] cycleEdge = findCycleEdge(edges, secondEdge);

        if (cycleEdge != null) {
            if (firstEdge != null) return firstEdge;
            else return cycleEdge;
        }
        return secondEdge;
    }

    private static int[] findCycleEdge(int[][] edges, int[] skipEdge) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        for (int[] edge : edges) {
            if (Arrays.equals(edge, skipEdge)) continue;
            int node1 = edge[0];
            int node2 = edge[1];

            int root1 = find(parent, node1);
            int root2 = find(parent, node2);

            if (root1 == root2) return edge;
            parent[root2] = root1;
        }
        return null;
    }

    private static int find(int[] parent, int node) {
        if (parent[node] == -1) return node;
        return parent[node] = find(parent, parent[node]);
    }
}
