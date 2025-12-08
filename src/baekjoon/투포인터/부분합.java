package baekjoon.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 부분합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = arr[0];
        int answer = Integer.MAX_VALUE;

        while (true) {
            if (sum >= s) {
                answer = Math.min(answer, right - left + 1);

                sum -= arr[left];
                left++;
                if (left > right && left < n) {
                    right = left;
                    sum = arr[left];
                }
            } else {
                right++;
                if (right == n) break;
                sum += arr[right];
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
