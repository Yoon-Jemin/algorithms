package baekjoon.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 P1253
public class ex8_좋은수 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());    // 수의 개수
        int[] A = new int[N];   // 수 데이터 저장 배열

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        int result = 0;

        for(int i = 0; i < N; i++){
            int start = 0;  // 시작 포인터
            int end = N-1;    // 끝 포인터
            int target = A[i];
            while(start < end){
                if(A[start] + A[end] == target){
                    if(start != i && end != i){
                        result++;
                        break;
                    } else if (start == i) {
                        start++;
                    } else if (end == i) {
                        end--;
                    }
                } else if (A[start] + A[end] < target){
                    start++;
                } else if (A[start] + A[end] > target) {
                    end--;
                }
            }
        }

        System.out.println(result);
    }
}
