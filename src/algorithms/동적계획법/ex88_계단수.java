package algorithms.동적계획법;

import java.util.Scanner;

// 백준 P10844
public class ex88_계단수 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] D = new int[n+1][10];

        D[1][0] = 0;
        for(int i = 1; i <= 9; i++){
            D[1][i] = 1;
        }

        for(int i = 2; i <= n; i++){
            for(int j = 0; j <= 9; j++){
                if(j == 0) D[i][j] = D[i-1][j];
                else if (j == 9) D[i][j] = D[i-1][j];
                else D[i][j] = 2 * D[i-1][j];
            }
        }

        int sum = 0;
        for(int i = 0; i <= 9; i++){
            sum += D[n][i];
        }

        System.out.println(sum);
    }
}
