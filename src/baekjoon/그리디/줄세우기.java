package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 줄세우기 {

    public static void main(String[] args) throws IOException {
        // 줄 서있는 어린이 중 한 명을 선택하여 제일 앞이나 제일 뒤로 보낸다
        // 어린이가 이동해서 빈자리가 생기는 경우에는 빈자리의 뒤에 있는 어린이들이 한 걸음씩 앞으로 걸어와서 빈자리를 메꾼다

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] dp = new int[n + 1];  // dp[i] = 숫자 i로 끝나는 연속 수열 길이

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            int num = arr[i];

            dp[num] = dp[num - 1] + 1;
            maxLen = Math.max(maxLen, dp[num]);
        }

        System.out.println(n - maxLen);
    }
}
