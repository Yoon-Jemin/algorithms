package baekjoon.수학;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 이항계수3 {

    static int MOD = 1_000_000_007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        Long[] factorial = new Long[n + 1];
        factorial[0] = 1L;

        for (int i = 1; i <= n; i++) {
            factorial[i] = (factorial[i - 1] * i) % MOD;
        }

        Long result = factorial[n] * reverse(factorial[k]) % MOD;
        result = result * reverse(factorial[n - k]) % MOD;
        System.out.println(result);
    }

    private static long reverse(Long a) {
        return pow(a, MOD - 2);
    }

    private static long pow(long a, long b) {
        long result = 1;
        while (b > 0) {
            if ((b & 1) == 1) result = result * a % MOD;
            a = (a * a) % MOD;
            b >>= 1;
        }
        return result;
    }
}
