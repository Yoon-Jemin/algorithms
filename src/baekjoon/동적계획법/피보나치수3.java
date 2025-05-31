package baekjoon.동적계획법;

import java.util.Scanner;

public class 피보나치수3 {

    static int MOD = 1_000_000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        if (n == 0) {
            System.out.println(0);
            return;
        }

        long[][] result = pow(new long[][]{{1, 1}, {1, 0}}, n - 1);
        System.out.println(result[0][0]);
    }

    static long[][] pow(long[][] matrix, long n) {
        long[][] result = new long[][]{{1, 0}, {0, 1}};
        while (n > 0) {
            if (n % 2 == 1) {
                result = multiply(result, matrix);
            }
            matrix = multiply(matrix, matrix);
            n /= 2;
        }

        return result;
    }

    static long[][] multiply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        result[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD;
        result[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
        result[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
        result[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;
        return result;
    }
}
