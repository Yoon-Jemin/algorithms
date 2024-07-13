package baekjoon.자료구조;

import java.util.Scanner;

// 백준 P10986
public class ex5_나머지합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();   // 입력 받을 수열의 개수
        int M = sc.nextInt();   // 나우어 떨어져야 하는 수
        int answer = 0;

        int[] A = new int[N];   // 주어진 수열
        for(int i = 0; i < N; i++){
            A[i] = sc.nextInt();
        }

        int[] S = new int[N];   // 합 배열
        S[0] = A[0];
        for(int i = 1; i < N; i++){
            S[i] = S[i-1] + A[i];
        }

        int[] C = new int[M];   // 합 배열을 M으로 나눴을 때 나오는 값의 횟수를 저장하는 배열
        for(int num : S){
            int remainder = num % M;
            if(remainder == 0) answer++;
            C[remainder]++;
        }

        for(int num : C){
            if(num >= 2){
                int result = num * (num-1) / 2;
                answer += result;
            }
        }

        System.out.println(answer);
    }
}
