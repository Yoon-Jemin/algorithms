package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 행렬의곱셈 {

    static int n;
    static int mod = 1_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        long exp = Long.parseLong(st.nextToken());

        long[][] arr = new long[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Long.parseLong(st.nextToken());
            }
        }

        long[][] result = new long[n][n];
        for (int i = 0; i < n; i++) result[i][i] = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) result = multiply(result, arr);
            arr = multiply(arr, arr);
            exp >>= 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static long[][] multiply(long[][] a, long[][] b) {
        long[][] result = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += (a[i][k] * b[k][j]);
                }
                result[i][j] %= mod;
            }
        }
        return result;
    }
}
