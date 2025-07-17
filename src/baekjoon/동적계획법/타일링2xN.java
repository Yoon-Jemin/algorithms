package baekjoon.동적계획법;

import java.util.Scanner;

public class 타일링2xN {

    static int DIVISOR = 10007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n == 1 || n == 2) {
            System.out.println(n);
            return;
        }

        int[] arr = new int[n + 1];

        arr[1] = 1;
        arr[2] = 2;

        for (int i = 3; i <= n; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % DIVISOR;
        }

        System.out.println(arr[n]);
    }
}
