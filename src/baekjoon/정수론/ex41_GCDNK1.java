package baekjoon.정수론;

import java.util.Scanner;

// 백준 P11689
public class ex41_GCDNK1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int result = N;

        for(int i = 2; i < Math.sqrt(N); i++){
            if(N % i == 0){     // i가 N의 인수
                result = result - result/i;
                while (N % i == 0) N /= i;
            }
        }

        if(N > 1) result = result - result/N;

        System.out.println(result);
    }
}
