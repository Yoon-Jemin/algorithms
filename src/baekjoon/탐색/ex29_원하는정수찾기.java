package baekjoon.탐색;

import java.util.Arrays;
import java.util.Scanner;

// 백준 P1920
public class ex29_원하는정수찾기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();   // 정렬할 수 개수
        int[] A = new int[N];

        for(int i = 0; i < N; i++) A[i] = sc.nextInt();
        Arrays.sort(A);

        int M = sc.nextInt();   // 탐색할 수의 개수
        for(int i = 0; i < M; i++){
            boolean find = false;
            int target = sc.nextInt();
            int start = 0;
            int end = A.length -1;
            while (start <= end){
                int mid = start + (end - start)/2;
                if(A[mid] > target){
                    end = mid - 1;
                } else if (A[mid] < target) {
                    start = mid + 1;
                } else {
                    find = true;
                    break;
                }
            }

            if(find) System.out.println(1);
            else System.out.println(0);
        }
    }
}
