package baekjoon.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 행성연결 {

    public static class UnionFind {
        int n;
        int[] parent;
        int[] rank;

        public UnionFind (int n) {
            this.n = n;
            parent = new int[n + 1];
            rank = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);
        for (int start = 1; start <= n; start++) {
            st = new StringTokenizer(br.readLine());
            for (int end = 1; end <= n; end++) {
                int cost = Integer.parseInt(st.nextToken());
                if (start < end) pq.add(new int[] {start, end, cost});
            }
        }

        long totalCost = 0;
        UnionFind uf = new UnionFind(n);
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int x = poll[0];
            int y = poll[1];
            int cost = poll[2];

            if (uf.find(x) == uf.find(y)) continue;

            uf.union(x, y);
            totalCost += cost;
        }

        System.out.println(totalCost);
    }
}
