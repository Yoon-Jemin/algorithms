package baekjoon.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 학교탐방하기 {

    static class UnionFind {
        int n;
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            this.n = n;
            parent = new int[n + 1];
            rank = new int[n + 1];

            for (int i = 0; i <= n; i++) {
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

    static int N, M;
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 건물의 개수
        M = Integer.parseInt(st.nextToken());   // 도로의 개수

        Deque<int[]> minRoads = new ArrayDeque<>();     // 오르막길 우선
        Deque<int[]> maxRoads = new ArrayDeque<>();     // 내리막길 우선
        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());   // 0: 오르막길, 1: 내리막길

            if (c == 0) {
                minRoads.addFirst(new int[] {a, b, c});
                maxRoads.addLast(new int[] {a, b, c});
            } else {
                minRoads.addLast(new int[] {a, b, c});
                maxRoads.addFirst(new int[] {a, b, c});
            }
        }

        simulate(minRoads);
        simulate(maxRoads);

        System.out.println(MAX - MIN);
    }

    private static void simulate(Deque<int[]> roads) {
        UnionFind uf = new UnionFind(N);
        int uphill = 0;

        while (!roads.isEmpty()) {   // 오르막길 우선
            int[] road = roads.pollFirst();

            int x = road[0];
            int y = road[1];
            int hill = road[2];

            if (uf.find(x) == uf.find(y)) continue;
            uf.union(x, y);

            if (hill == 0) uphill++;
        }

        MAX = Math.max(MAX, uphill * uphill);
        MIN = Math.min(MIN, uphill * uphill);
    }
}
