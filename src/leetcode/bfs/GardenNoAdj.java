package leetcode.bfs;

import java.util.*;

public class GardenNoAdj {

    public static void main(String[] args) {
        int n = 4;
        int[][] paths = {{1,2},{2,3},{3,4},{4,1},{1,3},{2,4}};
        int[] result = gardenNoAdj(n, paths);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] gardenNoAdj(int n, int[][] paths) {
        ArrayList<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            graph[path[0]].add(path[1]);
            graph[path[1]].add(path[0]);
        }

        Boolean[] visited = new Boolean[n + 1];
        Arrays.fill(visited, false);
        int[] flower = new int[n];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            queue.add(i);
            while (!queue.isEmpty()) {
                int cur = queue.poll();

                Set<Integer> neighborFlower = new HashSet<>();
                for (int next : graph[cur]) {
                    if (visited[next]) {
                        neighborFlower.add(flower[next - 1]);
                    } else {
                        visited[next] = true;
                        queue.add(next);
                    }
                }

                for (int k = 1; k <= 4; k++) {
                    if (neighborFlower.contains(k)) continue;
                    flower[cur - 1] = k;
                    break;
                }
            }
        }

        return flower;
    }
}
