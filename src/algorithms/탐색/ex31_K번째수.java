package algorithms.탐색;

import java.util.Scanner;

//백준 1300
public class ex31_K번째수 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();   // 배열의 크기
        int K = sc.nextInt();   // 구하고자하는 인덱스

        int start = 1;
        int end = K;
        int answer = 0;

        while (start <= end){
            int middle = (start + end) / 2;
            int cnt = 0;    // 중앙값보다 작은 수

            for(int i = 1; i <= N; i++){
                cnt += Math.min(N, middle/i);
            }

            if(cnt < K) start = middle + 1;
            else {
                end = middle - 1;
                answer = middle;
            }
        }

        System.out.println(answer);
    }
}
