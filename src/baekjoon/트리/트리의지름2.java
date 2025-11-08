package baekjoon.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 트리의지름2 {

    static int V;
    static ArrayList<int[]>[] graph;
    static int farthestNode = 0;
    static int maxDist1 = 0;
    static int maxDist2 = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            List<Integer> info = new ArrayList<>();
            while (true) {
                int num = Integer.parseInt(st.nextToken());

                if (num == -1) break;
                info.add(num);
            }

            for (int j = 0; j < info.size(); j += 2) {
                int next = info.get(j);
                int dist = info.get(j + 1);
                graph[start].add(new int[] {next, dist});
            }
        }

        int start = 0;
        for (int i = 1; i <= V; i++) {
            if (graph[i].size() == 1) {
                start = i;
                break;
            }
        }

        DFS1(start, 0, new HashSet<>());
        DFS2(farthestNode, 0, new HashSet<>());

        System.out.println(maxDist2);
    }

    private static void DFS1(int now, int dist, Set<Integer> visited) {
        if (dist >= maxDist1) {
            maxDist1 = dist;
            farthestNode = now;
        }

        visited.add(now);

        for (int[] n : graph[now]) {
            int next = n[0];
            int nextDist = n[1];

            if (visited.contains(next)) continue;
            DFS1(next, dist + nextDist, visited);
        }
    }

    private static void DFS2(int now, int dist, Set<Integer> visited) {
        if (dist >= maxDist2) {
            maxDist2 = dist;
        }

        visited.add(now);

        for (int[] n : graph[now]) {
            int next = n[0];
            int nextDist = n[1];

            if (visited.contains(next)) continue;
            DFS2(next, dist + nextDist, visited);
        }
    }


}
