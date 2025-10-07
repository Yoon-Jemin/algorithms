package baekjoon.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 이분그래프 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());   // 정점의 개수
            int E = Integer.parseInt(st.nextToken());   // 간선의 개수

            ArrayList<Integer>[] graph = new ArrayList[V + 1];
            for (int i = 1; i <= V; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            boolean isPossible = true;
            int[] color = new int[V + 1];

            for (int i = 1; i <= V; i++) {
                if (color[i] == 0) {
                    if (!BFS(i, color, graph)) {
                        isPossible = false;
                        break;
                    }
                }
            }

            if (isPossible) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    private static boolean BFS(int start, int[] color, ArrayList<Integer>[] graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            int nowColor = color[now];

            for (int next : graph[now]) {
                if (color[next] == 0) { // 방문되지 않음
                    color[next] = nowColor == 1 ? -1 : 1;
                    queue.add(next);
                } else if (color[next] == nowColor){    // 이미 방문됨
                    return false;
                }
            }
        }

        return true;
    }
}
