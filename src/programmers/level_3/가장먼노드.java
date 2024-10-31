package programmers.level_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 가장먼노드 {

    public static void main(String[] args) {

        int n = 6;
        int[][] edges = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n, edges));
    }

    public static int solution(int n, int[][] edges) {
        int[] D = new int[n+1];
        boolean[] visited = new boolean[n+1];
        ArrayList<Integer>[] graph = new ArrayList[n+1];

        for (int i = 1; i <= n; i++) {
            D[i] = Integer.MAX_VALUE;
            visited[i] = false;
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        D[1] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited[node] = true;
            ArrayList<Integer> neighbors = graph[node];
            for (Integer neighbor : neighbors) {
                if(!visited[neighbor]) {
                    visited[neighbor] = true;
                    D[neighbor] = D[node] + 1;
                    queue.add(neighbor);
                }
            }
        }

        int answer = 0;

        int max = 0;
        for(int i = 1; i <= n; i++) {
            max = Math.max(max, D[i]);
        }

        for(int i = 1; i <= n; i++) {
            if(D[i] == max) answer++;
        }

        return answer;
    }
}
