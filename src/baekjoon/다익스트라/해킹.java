package baekjoon.다익스트라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 해킹 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());

            int hackCount = 0;
            int hackTime = 0;

            int n = Integer.parseInt(st.nextToken());   // 컴퓨터의 개수
            int d = Integer.parseInt(st.nextToken());   // 의존성 개수
            int c = Integer.parseInt(st.nextToken());   // 해킹당한 컴퓨터의 번호

            ArrayList<int[]>[] graph = new ArrayList[n + 1];

            for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());

                graph[b].add(new int[]{a, dist});
            }

            Set<Integer> set = new HashSet<>();
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);    // 거리 기반 내림 차순
            queue.add(new int[] {c, 0});

            while (!queue.isEmpty()) {
                int[] poll = queue.poll();

                int now = poll[0];
                int nowDist = poll[1];

                if (set.contains(now)) continue;
                set.add(now);

                if (nowDist > hackTime) hackTime = nowDist;

                for (int[] next : graph[now]) {
                    if (set.contains(next[0])) continue;

                    queue.add(new int[] {next[0], nowDist + next[1]});
                }
            }

            hackCount = set.size();

            System.out.println(hackCount + " " + hackTime);
        }
    }
}
