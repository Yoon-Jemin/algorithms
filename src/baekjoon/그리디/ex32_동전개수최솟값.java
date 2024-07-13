package baekjoon.그리디;

import java.util.Scanner;

// 백준 P11047
public class ex32_동전개수최솟값 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 동전의 개수
        int K = sc.nextInt();   // 목표 금액

        int[] A = new int[N];
        for(int i =0; i < N; i++) A[i] = sc.nextInt();

        // 그리디 알고리즘
        int count = 0;  // 사용한 동전의 개수
        for(int i = N - 1; i >= 0; i--){
            if(A[i] <= K){
                count += K/A[i]; // 몫
                K = K % A[i];   // 나머지
            }
        }

        System.out.println(count);
    }
}
