package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 선긋기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new int[]{start, end});
        }

        int[] poll = pq.poll();
        int start = poll[0];
        int end = poll[1];
        int answer = 0;

        while (!pq.isEmpty()) {
            int[] next = pq.poll();

            int nextStart = next[0];
            int nextEnd = next[1];

            if (end < nextStart) {    // 줄이 끊김
                answer += end - start;  // 이전 줄 더하기
                start = nextStart;
                end = nextEnd;
            } else {    // 줄이 이어짐
                end = Math.max(end, nextEnd);
            }
        }

        answer += end - start;
        System.out.println(answer);
    }
}
