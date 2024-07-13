package algorithms.정수론;

import java.util.Scanner;

// 백준 P1456
public class ex38_거의소수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long Min = sc.nextInt();
        long Max = sc.nextInt();

        long[] A = new long[10000001];
        for(int i = 2; i <= 10000000; i++){     // 제곱근까지 실행
            A[i] = i;
        }

        for(int i = 2; i < Math.sqrt(A.length); i++){   // 배수 지우기
            if(A[i] == 0) continue;
            for(int j = i + i; j < A.length; j = j + i){
                A[j] = 0;
            }
        }

        int count = 0;
        for(int i = 2; i < 10000000; i++){
            if(A[i] != 0){
                long temp = A[i];
                while ((double)A[i] <= (double) Max / temp){
                    if((double)A[i] >= (double) Min / temp){
                        count++;
                    }
                    temp *= A[i];
                }
            }
        }

        System.out.println(count);
    }
}
