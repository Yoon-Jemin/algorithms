package baekjoon.자료구조;

import java.util.Scanner;

// 백준 P1546
public class ex2_평균 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int A[] = new int[N];

        for (int i = 0; i < N; i++){
            A[i] = sc.nextInt();
        }

        long sum = 0;
        long max = 0;

        for(int i = 0; i < A.length; i++){
            if(max < A[i]) max = A[i];
            sum += A[i];
        }

        System.out.println(sum* 100.0 / max / N);
    }
}
