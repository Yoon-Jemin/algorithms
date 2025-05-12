package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 마라톤1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] point = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            point[i][0] = Integer.parseInt(st.nextToken());
            point[i][1] = Integer.parseInt(st.nextToken());
        }

        int total = 0;
        for (int i = 0; i < n - 1; i++) {
            total += calculate(point[i], point[i + 1]);
        }

        int saved =Integer.MAX_VALUE;
        for (int i = 1 ; i < n - 1; i++) {
            int original = calculate(point[i - 1], point[i]) + calculate(point[i], point[i + 1]);
            int skipped = calculate(point[i - 1], point[i + 1]);
            saved = Math.min(saved, skipped - original);
        }

        System.out.println(total + saved);
    }

    private static int calculate(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
}
