package baekjoon.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 트리2 {

    static int N;
    static int erase;
    static ArrayList<Integer>[] tree;
    static int leaf = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int root = -1;
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) tree[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
                continue; // 루트 노드
            }

            tree[parent].add(i);
        }

        st = new StringTokenizer(br.readLine());
        erase = Integer.parseInt(st.nextToken());

        if (erase == root) {
            System.out.println(0);
            return;
        }

        DFS(root);
        System.out.println(leaf);
    }

    private static void DFS(int now) {
        if (now == erase) return;

        boolean hasChild = false;
        for (int child : tree[now]) {
            if (child == erase) continue;
            hasChild = true;
            DFS(child);
        }

        if (!hasChild) leaf++;
    }
}
