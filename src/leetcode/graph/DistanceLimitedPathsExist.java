package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class DistanceLimitedPathsExist {

    public static void main(String[] args) {
        int n = 5;
        int[][] edgeList = {{0,1,10},{1,2,5},{2,3,9},{3,4,13}};
        int[][] queries = {{0,4,14},{1,4,13}};
        boolean[] visited = distanceLimitedPathsExist(n, edgeList, queries);
        for (int i = 0; i < visited.length; i++) {
            System.out.print(visited[i] + " ");
        }
    }

    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList, Comparator.comparingInt(a -> a[2]));

        int m = queries.length;
        int[][] indexedQueries = new int[m][4]; // [p, q, limit, index]
        for (int i = 0; i < m; i++) {
            indexedQueries[i][0] = queries[i][0];
            indexedQueries[i][1] = queries[i][1];
            indexedQueries[i][2] = queries[i][2];
            indexedQueries[i][3] = i;
        }

        Arrays.sort(indexedQueries, Comparator.comparingInt(a -> a[2]));

        UnionFind uf = new UnionFind(n);
        boolean[] answer = new boolean[m];

        int edgeIndex = 0;
        for (int[] query : indexedQueries) {
            int p = query[0];
            int q = query[1];
            int limit = query[2];
            int idx = query[3];

            while (edgeIndex < edgeList.length && edgeList[edgeIndex][2] < limit) {
                int u = edgeList[edgeIndex][0];
                int v = edgeList[edgeIndex][1];
                uf.union(u, v);
                edgeIndex++;
            }

            answer[idx] = uf.find(p) == uf.find(q);
        }

        return answer;
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }
}
