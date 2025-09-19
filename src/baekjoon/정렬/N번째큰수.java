package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class N번째큰수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);   // 숫자, 열, 행
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (i == n - 1) {
                    pq.offer(new int[]{map[i][j], j, i});
                }
            }
        }

        int answer = 0;

        while (true) {
            answer++;
            int[] poll = pq.poll();
            int number = poll[0];
            int column = poll[1];
            int row = poll[2];

            if (answer == n) {
                System.out.println(number);
                break;
            }

            if (row > 0) {
                pq.offer(new int[]{map[row - 1][column], column, row - 1});
            }
        }
    }
}
