package baekjoon.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최종순위 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());    //  팀 개수
            int[] lastYear = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                lastYear[i] = Integer.parseInt(st.nextToken());
            }

            boolean[][] adj = new boolean[n + 1][n + 1];
            int[] indegree = new int[n + 1];

            for (boolean[] a : adj) Arrays.fill(a, false);

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int higher = lastYear[i];
                    int lower = lastYear[j];
                    if (!adj[higher][lower]) {
                        adj[higher][lower] = true;
                        indegree[lower]++;
                    }
                }
            }

            // 올해 순위 변경
            int m = Integer.parseInt(br.readLine());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (adj[a][b]) {
                    adj[a][b] = false;
                    indegree[b]--;
                    adj[b][a] = true;
                    indegree[a]++;
                } else {
                    adj[b][a] = false;
                    indegree[a]--;
                    adj[a][b] = true;
                    indegree[b]++;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if (indegree[i] == 0) queue.add(i);
            }

            List<Integer> result = new ArrayList<>();
            boolean certain = true;
            boolean impossible = false;

            for (int i = 0; i < n; i++) {
                if (queue.isEmpty()) {
                    impossible = true;
                    break;
                }
                if (queue.size() > 1) {
                    certain = false;
                }

                int cur = queue.poll();
                result.add(cur);

                for (int next = 1; next <= n; next++) {
                    if (adj[cur][next]) {
                        indegree[next]--;
                        if (indegree[next] == 0) queue.add(next);
                    }
                }
            }

            if (impossible) {
                sb.append("IMPOSSIBLE\n");
            } else if (!certain) {
                sb.append("?\n");
            } else {
                for (int x : result) sb.append(x).append(" ");
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
