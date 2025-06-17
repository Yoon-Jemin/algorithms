package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 한줄로서기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] input = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int[] line = new int[n];
        for (int i = 0; i < n; i++) {
            int nowHeight = i + 1;
            int bigger = input[i];

            int count = 0;
            int idx = 0;

            while (count < bigger) {
                if (line[idx] == 0) count++;
                idx++;
            }

            while (line[idx] != 0) {
                idx++;
            }

            line[idx] = nowHeight;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(line[i] + " ");
        }
    }
}
