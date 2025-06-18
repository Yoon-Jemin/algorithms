package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 보물 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 오름차순
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Comparator.reverseOrder());    // 내림 차순

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq1.add(Integer.parseInt(st.nextToken()));
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            answer += pq.poll() * pq1.poll();
        }

        System.out.println(answer);
    }
}
