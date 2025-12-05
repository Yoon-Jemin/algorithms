package baekjoon.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 도시분할계획 {

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

    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 집의 개수
        M = Integer.parseInt(st.nextToken());   // 길의 개수

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);   // 비용 기준 오름차순
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new int[] {a, b, cost});
        }

        UnionFind uf = new UnionFind(N);
        int totalCost = 0;
        int lastCost = 0;

        // 전체 마을을 최소 비용으로 연결함
        while (!pq.isEmpty()) {
            int[] road = pq.poll();

            int x = road[0];
            int y = road[1];
            int cost = road[2];

            int rootX = uf.find(x);
            int rootY = uf.find(y);
            if (rootX == rootY) continue;

            uf.union(rootX, rootY);
            totalCost += cost;

            lastCost = cost;
        }

        System.out.println(totalCost - lastCost);
     }
}
