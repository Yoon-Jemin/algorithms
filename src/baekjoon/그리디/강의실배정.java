package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 강의실배정 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> rooms = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.add(new int[]{start, end});
        }

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int start = poll[0];
            int end = poll[1];

            if (rooms.isEmpty()) {
                rooms.add(end);
            } else {
                if (rooms.peek() > start) {     //  강의실 추가로 필요
                    rooms.add(end);
                } else {
                    rooms.poll();
                    rooms.add(end);
                }
            }
        }

        System.out.println(rooms.size());
    }
}
