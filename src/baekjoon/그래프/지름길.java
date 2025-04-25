package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 지름길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 지름길의 개수
        int end = Integer.parseInt(st.nextToken());

        List<int[]>[] shortcuts = new ArrayList[10001];
        for (int i = 0; i <= 10000; i++) {
            shortcuts[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if (to > end) continue;
            if (to - from < distance) continue;

            shortcuts[from].add(new int[]{to, distance});
        }

        int[] distance = new int[end + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;

        for (int i = 0; i <= end; i++) {
            if (i > 0) {
                distance[i] = Math.min(distance[i], distance[i - 1] + 1);
            }

            for (int[] shortcut : shortcuts[i]) {
                int to = shortcut[0];
                int dist = shortcut[1];
                if (to > end) continue;
                distance[to] = Math.min(distance[to], distance[i] + dist);
            }
        }

        System.out.println(distance[end]);
    }
}

