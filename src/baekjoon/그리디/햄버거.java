package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 햄버거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        String input = st.nextToken();
        int[] line = new int[n];
        for (int i = 0; i < n; i++) {
            if (input.charAt(i) == 'H') line[i] = 1;
            else line[i] = 0;
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (line[i] == 0) {
                for (int j = i - k; j <= i + k; j++) {
                    if (j < 0 || j >= n) continue;
                    if (line[j] == 1) {
                        answer++;
                        line[j] = -1;
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
