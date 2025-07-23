package baekjoon.탐색;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자카드 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            int left = 0;
            int right = nums.length - 1;

            boolean flag = false;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    flag = true;
                    break;
                }
            }

            if (flag) bw.write(1 + " ");
            else bw.write(0 + " ");
        }

        bw.flush();
        bw.close();
    }
}
