package baekjoon.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 트리 {

    public static class UnionFind {
        int n;
        int[] parent;
        int[] rank;
        Set<Integer> notForest;

        public UnionFind(int n) {
            this.n = n;
            this.parent = new int[n + 1];
            this.rank = new int[n + 1];
            this.notForest = new HashSet<>();

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

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }

        public int getForest() {
            Set<Integer> forests = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                int parent = find(i);
                if (notForest.contains(parent)) continue;
                forests.add(parent);
            }
            return forests.size();
        }
    }

    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());   // 정점의 개수
            M = Integer.parseInt(st.nextToken());   // 간선의 개수

            if (N == 0 && M == 0) break;

            UnionFind uf = new UnionFind(N);

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                int rootX = uf.find(x);
                int rootY = uf.find(y);

                if (rootX == rootY) {
                    uf.notForest.add(rootX);
                } else {
                    uf.union(x, y);
                }
            }

            int forest = uf.getForest();

            if (forest == 1) {
                System.out.println("Case " + idx + ": There is one tree.");
            } else if (forest > 1){
                System.out.println("Case " + idx + ": A forest of " + forest + " trees.");
            } else {
                System.out.println("Case " + idx + ": No trees.");
            }

            idx++;
        }
    }
}
