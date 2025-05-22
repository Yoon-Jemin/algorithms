package leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class IsUgly {

    public static void main(String[] args) {
        System.out.println(isUgly(6));
    }

    public static boolean isUgly(int n) {
        if (n == 1) return true;
        if (n <= 0) return false;

        int[] primes = new int[3];
        primes[0] = 2;
        primes[1] = 3;
        primes[2] = 5;

        while (n > 1) {
            boolean flag = false;
            for (int prime : primes) {
                if (n % prime == 0) {
                    n /= prime;
                    flag = true;
                }
            }
            if (!flag) return false;
        }

        return true;
    }
}
