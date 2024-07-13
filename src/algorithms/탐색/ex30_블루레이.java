package algorithms.탐색;

import java.util.Scanner;

// 백준 P2343 블루레이
public class ex30_블루레이 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();   // 레슨 수
        int M = sc.nextInt();   // 블루레이 개수
        int[] A = new int[N];

        int start = 0;  // 시작 인덱스
        int end = 0;    // 끝 인덱스

        for(int i = 0; i < N; i++){
            A[i] = sc.nextInt();    // 레슨의 길이
            if(start < A[i]) start = A[i];  // 시작 인덱스는 레슨 길이가 가장 큰 값
            end += A[i];    // 끝 인덱스는 레슨 길이의 총합
        }

        while (start <= end){
            int mid = (start + end) / 2;
            int sum = 0;    // 레슨 합
            int count = 0;  // 사용한 블루레이 개수

            for(int i = 0; i < N; i++){
                if(sum + A[i] > mid){
                    count++;
                    sum = 0;
                }
                sum += A[i];
            }

            if(sum != 0) count++;

            if(count > M) start = mid + 1;
            else  end = mid - 1;
        }

        System.out.println(start);
    }
}
