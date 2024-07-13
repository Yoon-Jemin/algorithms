package baekjoon.그리디;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// 백준 P1931
public class ex35_회의실배정 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 회의 개수
        int[][] A = new int[N][2];

        for(int i = 0; i < N; i++){
            A[i][0] = sc.nextInt();
            A[i][1] = sc.nextInt();
        }

        Arrays.sort(A, new Comparator<int[]>() {
            @Override
            public int compare(int[] S, int[] E) {
                if (S[1] == E[1]){
                    return S[0] - E[0];
                }
                return S[1] - E[1];
            }
        });

        int count = 0;
        int end = -1;

        for(int i = 0; i < N; i++){
            if(A[i][1] > end){
                count++;
                end = A[i][1];
            }
        }

        System.out.println(count);
    }
}
