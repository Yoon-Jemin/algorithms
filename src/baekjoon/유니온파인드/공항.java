package baekjoon.유니온파인드;

import java.util.Scanner;

public class 공항 {

    public static class UnionFind {
        private int[] parent;

        public UnionFind(int size) {
            parent = new int[size + 1];
            for (int i = 0; i <= size; i++) parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }

        public void union(int a, int b) {
            a = find(a);
            b = find(b);
            parent[a] = b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   // 게이트의 개수
        int m = sc.nextInt();   // 비행기의 개수
        int answer = 0;
        UnionFind uf = new UnionFind(n);

        for (int plane = 1 ; plane <= m ; plane++) {
            int g = sc.nextInt();   // 1번 부터 g번 게이트에 도킹 가능

            int available = uf.find(g);
            if (available == 0) break;
            uf.union(available, available - 1);
            answer++;
        }

        System.out.println(answer);
    }
}
