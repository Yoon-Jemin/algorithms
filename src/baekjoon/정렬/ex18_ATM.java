package baekjoon.정렬;

import java.util.Scanner;

// 백준 P11399 (삽입 정렬)
public class ex18_ATM {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();   //  데이터 개수
        int[] A = new int[N];   // 데이터를 저장하는 배열
        int[] S = new int[N];   // 합 배열
        for(int i = 0; i < N; i++){
            A[i] = sc.nextInt();;
        }

        for (int i = 1; i < N; i++){

            int flag = 0;
            int insert_point = 0;       // 삽입할 위치
            int insert_value = A[i];     // 삽입해야 하는 값

            for(int j = 0; j < i; j++){ // A[i+1]을 정렬된 부분(A[0]부터 A[i]까지)을 모두 비교하여 삽입할 위치 찾기
                if(A[i] < A[j]){
                    flag = 1;
                    insert_point = j;   // A[i+1]를 삽입할 위치는 j
                    break;
                }
            }

            if(flag == 1){
                for(int k = i; k > insert_point; k--){  // temp부터 i까지 한 칸 옆으로 밀기
                    A[k] = A[k-1];
                }
                A[insert_point] = insert_value;
            }

        }

        S[0] = A[0];
        for(int i =1; i < N; i++){
            S[i] = S[i-1] + A[i];
        }

        int sum = 0;
        for(int i =0; i < N; i++){
            sum += S[i];
        }

        System.out.println(sum);
    }
}
