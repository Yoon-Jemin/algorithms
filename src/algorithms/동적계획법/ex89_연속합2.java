package algorithms.동적계획법;

import java.util.Scanner;

// 백준 P13398
public class ex89_연속합2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] A = new int[n];
        int[] L = new int[n];
        int[] R = new int[n];

        for(int i = 0; i < n; i++){
            A[i] = sc.nextInt();
        }

        L[0] = A[0];
        int result = L[0];
        for(int i = 1; i < n; i++){
            L[i] = Math.max(A[i], A[i] + L[i-1]);
            result = Math.max(result, L[i]);
        }

        R[n-1] = A[n-1];
        for(int i = n-2; i > 0; i--){
            R[i] = Math.max(A[i], A[i] + R[i+1]);
        }

        for(int i = 1; i < n-2; i++){
            result = Math.max(result, L[i-1] + R[i+1]);
        }

        System.out.println(result);

    }
}
