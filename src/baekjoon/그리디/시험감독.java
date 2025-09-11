package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 시험감독 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 시험장의 개수

        st = new StringTokenizer(br.readLine());

        int[] examClasses = new int[n];
        for (int i = 0; i < n; i++) {
            examClasses[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int main = Integer.parseInt(st.nextToken());
        int sub = Integer.parseInt(st.nextToken());
        long answer = 0;

        for (int examClass : examClasses) {
            answer++;
            examClass -= main;
            if (examClass > 0) {
                answer += (examClass % sub == 0) ? examClass / sub : examClass / sub + 1;
            }
        }

        System.out.println(answer);
    }
}
