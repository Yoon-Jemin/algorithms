package baekjoon.동적계획법;

import java.util.Scanner;

// 백준 P14501
public class ex85_퇴사 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] D = new int[N+2];     // 퇴사일까지 벌어들일 수 있는 수입
        int[] T = new int[N+1];     // 상담하는데 걸리는 시간
        int[] P = new int[N+1];     // 상담으로 벌어들일 수 있는 수입

        D[N+1] = 0;

        for(int i = 1; i <= N; i++){
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }

        for(int i = N; i >= 1; i--){
            if(i + T[i] -1 > N){
                D[i] = D[i+1];
            }else{
                D[i] = Math.max(D[i+1], D[i+T[i]] + P[i]);
            }
        }

        System.out.println(D[1]);
    }
}
