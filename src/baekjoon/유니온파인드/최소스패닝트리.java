package baekjoon.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최소스패닝트리 {

    public static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size + 1];
            rank = new int[size + 1];
            for (int i = 1; i <= size; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return; // 이미 같은 집합

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    static int V, E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());   // 정점의 개수
        E = Integer.parseInt(st.nextToken());   // 간선의 개수

        List<int[]> routes = new ArrayList<>();
        int answer = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            routes.add(new int[]{a, b, dist});
        }

        Collections.sort(routes, (a, b) -> a[2] - b[2]);    // 거리 기준으로 오름 차순
        UnionFind uf = new UnionFind(V);

        for (int[] route : routes) {
            int node1 = route[0];
            int node2 = route[1];
            int dist = route[2];

            if (uf.find(node1) == uf.find(node2)) continue; // 이미 같은 그룹

            uf.union(node1, node2);
            answer += dist;
        }

        System.out.println(answer);
    }
}
