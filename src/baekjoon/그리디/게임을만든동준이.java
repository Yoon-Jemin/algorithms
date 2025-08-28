package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게임을만든동준이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            score[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = n - 1; i >= 1; i--) {
            int now = score[i];
            int prev = score[i - 1];

            if (now <= prev) {
                answer += (prev - now) + 1;
                score[i - 1] = now - 1;
            }
        }

        System.out.println(answer);
    }
}
