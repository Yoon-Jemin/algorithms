package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 맥주 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            int numStore = Integer.parseInt(br.readLine());
            int[][] map = new int[numStore + 2][2];

            for (int j = 0; j < numStore + 2; j++) {
                st = new StringTokenizer(br.readLine());
                map[j][0] = Integer.parseInt(st.nextToken());
                map[j][1] = Integer.parseInt(st.nextToken());
            }

            List<Integer>[] graph = new List[numStore + 2];
            for (int j = 0; j < numStore + 2; j++) graph[j] = new ArrayList<>();

            for (int j = 0; j < numStore + 2; j++) {
                for (int k = j + 1; k < numStore + 2; k++) {
                    if (isClose(map[j], map[k])) {
                        graph[k].add(j);
                        graph[j].add(k);
                    }
                }
            }

            boolean isPossible = false;

            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            visited.add(0);
            while (!queue.isEmpty()) {
                int now = queue.poll();

                if (now == numStore + 1) {
                    isPossible = true;
                    break;
                }

                for (int next : graph[now]) {
                    if (!visited.contains(next)) {
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }

            if (!isPossible) System.out.println("sad");
            else System.out.println("happy");
        }
    }

    private static boolean isClose(int[] a, int[] b) {
        if (Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]) <= 1000) return true;
        return false;
    }
}
