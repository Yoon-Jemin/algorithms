package baekjoon.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 민서의응급수술 {

    public static class UnionFind {
        int n;
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            this.n = n;
            this.parent = new int[n + 1];
            this.rank = new int[n + 1];

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

        int n = Integer.parseInt(st.nextToken());   // 정점의 개수
        int m = Integer.parseInt(st.nextToken());   // 간선의 개수

        UnionFind uf = new UnionFind(n);
        int answer = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int rootX = uf.find(Integer.parseInt(st.nextToken()));
            int rootY = uf.find(Integer.parseInt(st.nextToken()));

            if (rootX == rootY) {
                answer++;
                continue;
            }
            uf.union(rootX, rootY);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(uf.find(uf.parent[i]));
        }

        System.out.println(answer + set.size() - 1);
    }
}
