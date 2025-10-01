package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 회의실배정 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 회의 개수

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];  // 끝나는 시간이 같으면 시작 시간 오름차순
            return a[1] - b[1];  // 끝나는 시간 기준 정렬
        });   // 끝나는 시간 기준 오름차순 정렬

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.add(new int[]{start, end});
        }

        int answer = 0;
        int lastEndTime = 0;

        int[] startMeeting = pq.poll();
        answer++;
        lastEndTime = startMeeting[1];

        while (!pq.isEmpty()) {
            int[] meeting = pq.poll();
            int start = meeting[0];
            int end = meeting[1];

            if (start < lastEndTime) continue;

            lastEndTime = end;
            answer++;
        }

        System.out.println(answer);
    }
}
