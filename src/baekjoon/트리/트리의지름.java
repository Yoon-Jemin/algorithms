package baekjoon.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 트리의지름 {

    // 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이
    // 루트 노드의 번호는 항상 1

    static int maxDist1 = 0;
    static int maxDist2 = 0;
    static int node1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 노드의 개수
        if (n == 1) { // 노드가 하나면 지름은 0
            System.out.println(0);
            return;
        }

        ArrayList<int[]>[] tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            tree[a].add(new int[] {b, dist});
            tree[b].add(new int[] {a, dist});
        }

        Set<Integer> visited1 = new HashSet<>();
        visited1.add(1);
        find(1, 0, tree, visited1,1);

        Set<Integer> visited2 = new HashSet<>();
        visited2.add(node1);
        find(node1, 0, tree, visited2, 2);


        System.out.println(maxDist2);
    }

    private static void find(int node, int nowDist, ArrayList<int[]>[] tree, Set<Integer> visited, int type) {
        for (int[] n : tree[node]) {
            int next = n[0];
            int dist = n[1];

            if (visited.contains(next)) continue;

            visited.add(next);
            find(next, nowDist + dist, tree, visited, type);
        }

        if (type == 1) {
            if (nowDist > maxDist1) {
                maxDist1 = nowDist;
                node1 = node;
            }
        } else if (type == 2) {
            if (nowDist > maxDist2) {
                maxDist2 = nowDist;
            }
        }
    }
}
