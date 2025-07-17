package baekjoon.동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 더하기123 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[12];
        answer[1] = 1;
        answer[2] = 2;
        answer[3] = 4;

        for (int i = 4; i <= 11; i++) {
            answer[i] = answer[i - 1] + answer[i - 2] + answer[i - 3];
        }

        for (int num : nums) System.out.println(answer[num]);
    }
}
