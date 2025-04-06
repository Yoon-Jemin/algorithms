package baekjoon.바킹독;

import java.util.*;

public class 회장뽑기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 회원 수 : 5

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (a == -1 || b == -1) break;
            graph[a].add(b);
            graph[b].add(a);
        }

        int[] score = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            Set<Integer> visited = new HashSet<>();
            score[i] = BFS(i, graph, visited);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if (score[i] < min) min = score[i];
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (score[i] == min) list.add(i);
        }

        System.out.println(min + " " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    private static int BFS(int now, ArrayList<Integer>[] graph, Set<Integer> visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{now, 0});
        visited.add(now);

        int depth = 0;
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int nowNode = node[0];
            int nowDepth = node[1];
            depth = Math.max(nowDepth, depth);

            for (int nextNode : graph[nowNode]) {
                if (visited.contains(nextNode)) continue;
                queue.add(new int[]{nextNode, nowDepth + 1});
                visited.add(nextNode);
            }
        }

        return depth;
    }
}
