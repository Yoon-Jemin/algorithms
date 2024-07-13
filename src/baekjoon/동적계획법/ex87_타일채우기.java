package baekjoon.동적계획법;

import java.util.Scanner;

// 백준 P11716
public class ex87_타일채우기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] result = new int[n+1];
        result[1] = 1;
        result[2] = 2;

        for(int i = 3; i <= n; i++){
            result[i] = result[i-1] + result[i-2];
        }

        int mod = 10007;
        System.out.println(result[n] % mod);


    }
}

