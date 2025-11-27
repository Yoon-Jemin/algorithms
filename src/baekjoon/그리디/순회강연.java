package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 순회강연 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 대학 수

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            if(a[1] != b[1]) return b[1] - a[1];
            else return b[0] - a[0];
        });     // 날짜, 강연료

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());   // p만큼의 강연료 지불
            int d = Integer.parseInt(st.nextToken());   // d일 안에 강연을 해주면

            pq.add(new int[] {d, p});
        }

        boolean[] used = new boolean[10001];
        int answer = 0;

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();

            int day = poll[0];
            int money = poll[1];

            for (int d = day; d >= 1; d--) {
                if (!used[d]) {
                    used[d] = true;
                    answer += money;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
