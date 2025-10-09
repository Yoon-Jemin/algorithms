package baekjoon.동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 상자넣기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] boxes = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        // i번째 상자를 마지막으로 했을 때 넣을 수 있는 상자의 최대 개수
        int[] dp = new int[n];
        Arrays.fill(dp, 1);;

        for (int i = 1; i < n; i++) {   // 기준
            for (int j = 0; j < i; j++) {   // 이전 박스들
                if (boxes[i] > boxes[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int box : dp) max = Math.max(max, box);

        System.out.println(max);
    }
}
