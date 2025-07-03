package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100001];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int now = poll[0];
            int count = poll[1];

            if (now == end) {
                System.out.println(count);
                return;
            }

            int[] nexts = {now - 1, now + 1, now * 2};

            for (int next : nexts) {
                if (next >= 0 && next <= 100000 && !visited[next]) {
                    queue.add(new int[]{next, count + 1});
                    visited[next] = true;
                }
            }
        }
    }
}
