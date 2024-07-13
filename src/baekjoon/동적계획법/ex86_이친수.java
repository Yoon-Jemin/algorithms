package baekjoon.동적계획법;

import java.util.Scanner;

// 백준 P2193
public class ex86_이친수 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] D = new int[n+1][2];

        D[1][0] = 0;
        D[1][1] = 1;

        for(int i = 2; i <= n; i++){
            D[i][0] = D[i-1][0] + D[i-1][1];
            D[i][1] = D[i-1][0];
        }

        System.out.println(D[n][0] + D[n][1]);
    }
}
