package leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class IsBipartite {

    public static void main(String[] args) {
        int[][] graph = {
                {1,3}, {0,2}, {1,3}, {0,2}
        };
        System.out.println(isBipartite(graph));
    }

    public static boolean isBipartite(int[][] graph) {
        int[] group = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (group[i] != 0) continue;    // 이미 그룹을 배정 받음
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            group[i] = 1;
            while (!queue.isEmpty()) {
                int now = queue.poll();
                for (int j = 0; j < graph[now].length; j++) {
                    if (group[graph[now][j]] == 0) {
                        if (group[now] == 1) group[graph[now][j]] = 2;
                        if (group[now] == 2) group[graph[now][j]] = 1;
                        queue.add(graph[now][j]);
                    } else {    // 이미 방문함
                        if (group[now] == group[graph[now][j]]) return false;
                    }
                }
            }
        }

        return true;
    }
}
