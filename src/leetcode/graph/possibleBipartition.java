package leetcode.graph;

import java.util.*;

public class possibleBipartition {
    public static void main(String[] args) {
        int n = 4;
        int[][] dislikes = {{1,2}, {1,3}, {2,4}};
        System.out.println(possibleBipartition(n, dislikes));
    }

    public static boolean possibleBipartition(int n, int[][] dislikes) {
        if (n == 1) return true;
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] dislike : dislikes) {
            graph[dislike[0]].add(dislike[1]);
            graph[dislike[1]].add(dislike[0]);
        }

        int[] groupNum = new int[n+1];
        Arrays.fill(groupNum, -1);

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (groupNum[i] != -1) continue;
            queue.add(i);
            groupNum[i] = 0;

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int curGroup = groupNum[cur];

                for (int next : graph[cur]) {
                    if (groupNum[next] == -1) {     // 방문하지 않은 노드
                        if (curGroup == 0) groupNum[next] = 1;
                        else groupNum[next] = 0;
                        queue.add(next);
                    } else {    // 이미 방문한 노드 (그룹이 정해진 노드)
                        if (groupNum[next] == curGroup) return false;
                    }
                }
            }
        }

        return true;
    }
}
