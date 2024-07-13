package algorithms.동적계획법;

import java.util.Scanner;

// 백준 P1463
public class ex84_1로만들기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] D = new int[N+1];
        D[1] = 0;

        for(int i = 2; i <= N; i++){
            D[i] = D[i-1] + 1;
            if(i % 2 == 0) D[i] = Math.min(D[i], D[i/2]+1);
            if(i % 3 == 0) D[i] = Math.min(D[i], D[i/3]+1);
        }

        System.out.println(D[N]);
    }
}
