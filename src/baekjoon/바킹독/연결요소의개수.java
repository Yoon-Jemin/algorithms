package baekjoon.바킹독;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 연결요소의개수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 정점의 개수
        int m = Integer.parseInt(st.nextToken());   // 간선의 개수

        int[] parent = new int[n+1];
        int[] size = new int[n+1];

        for (int i = 0; i <= n ; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b, parent, size);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(find(i, parent));
        }

        System.out.println(set.size());
    }

    private static int find(int node, int[] parent) {
        if (parent[node] != node) {
            parent[node] = find(parent[node], parent);
        }
        return parent[node];
    }

    private static void union(int a, int b, int[] parent, int[] size) {
        int parentA = find(a, parent);
        int parentB = find(b, parent);

        if (parentA == parentB) return;
        if (size[parentA] >= size[parentB]) {
            parent[parentB] = parentA;
            size[parentA] += size[parentB];
        } else {
            parent[parentA] = parentB;
            size[parentB] += size[parentA];
        }
    }
}
