package baekjoon.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class WateringFields {

    public static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
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

    static int N, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // field의 개수
        C = Integer.parseInt(st.nextToken());   // 최소 비용

        int[][] fields = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            fields[i][0] = x;
            fields[i][1] = y;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int distance = getDistance(fields[i], fields[j]);

                if (distance >= C) {
                    pq.add(new int[]{i, j, distance});
                }
            }
        }

        int sum = 0;
        UnionFind uf = new UnionFind(N);
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int field1 = poll[0];
            int field2 = poll[1];
            int distance = poll[2];

            if (uf.find(field1) == uf.find(field2)) continue;   // 이미 같은 집합

            uf.union(field1, field2);
            sum += distance;
        }

        int parent = uf.parent[0];
        for (int p : uf.parent) {
            if (p != parent) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(sum);
    }

    private static int getDistance(int[] field1, int[] field2) {
        int x = Math.abs(field1[0] - field2[0]);
        int y = Math.abs(field1[1] - field2[1]);

        return x*x + y*y;
    }
}
