package baekjoon.정수론;

import java.util.Scanner;

// 백준 P1016
public class ex40_제곱이아닌수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Min = sc.nextInt();
        int Max = sc.nextInt();

        boolean[] check = new boolean[(int) (Max - Min + 1)];

        for(int i = 2; i*i <= Max; i++){
            int pow = i * i;

            int start = Min/pow;
            if(Min % pow != 0) start++;     // 나머지가 있으면 1을 더해야 Min 보다 큰 제곱수에서 시작됨

            for(int j = start; pow*j < Max; j++){
                check[(int) ((j*pow) - Min)] = true;
            }
        }

        int count = 0;
        for(int i = 0; i <= Max - Min; i++){
            if(!check[i]) count++;
        }

        System.out.println(count);
    }
}
