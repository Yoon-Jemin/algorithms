package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] list = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());

            int low = 0;
            int high = list.length - 1;
            int isExist = 0;

            while (low <= high) {
                int mid = (low + high) / 2;
                int num = list[mid];

                if (num == target) {
                    isExist = 1;
                    break;
                }

                if (num > target) high = mid - 1;
                else low = mid + 1;
            }

            System.out.println(isExist);
        }
    }
}
