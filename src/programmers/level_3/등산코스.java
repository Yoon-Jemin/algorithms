package programmers.level_3;

import java.util.*;

public class 등산코스 {

    public static void main(String[] args) {
        int n = 6;
        int[][] paths = {
                {1, 2, 3},
                {2, 3, 5},
                {2, 4, 2},
                {2, 5, 4},
                {3, 4, 4},
                {4, 5, 3},
                {4, 6, 1},
                {5, 6, 1}
        };

        int[] gates = {1, 3};
        int[] summits = {5};
        int[] result = solution(n, paths, gates, summits);  // {5, 3}
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<>();

        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int distance = path[2];
            graph[a].add(new int[]{b, distance});
            graph[b].add(new int[]{a, distance});
        }

        Set<Integer> gateSet = new HashSet<>();
        for (int gate : gates) gateSet.add(gate);

        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) summitSet.add(summit);

        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> (a[1] - b[1]));  // 현재 위치 : 최대 거리
        for (int gate : gates) {
            intensity[gate] = 0;
            pq.add(new int[]{gate, 0});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int inten = cur[1];

            if (inten > intensity[node]) continue;
            if (summitSet.contains(node)) continue;

            for (int[] next : graph[node]) {
                int nextNode = next[0];
                int weight = next[1];
                int newIntensity = Math.max(inten, weight);

                if (newIntensity < intensity[nextNode]) {
                    intensity[nextNode] = newIntensity;
                    pq.add(new int[]{nextNode, intensity[nextNode]});
                }
            }
        }

        int minSummit = 0;
        int minIntensity = Integer.MAX_VALUE;
        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minIntensity = intensity[summit];
                minSummit = summit;
            }
        }

        return new int[]{minSummit, minIntensity};
    }
}
