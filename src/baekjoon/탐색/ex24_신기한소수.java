package baekjoon.탐색;

import java.util.Scanner;

// 백준 P2023
public class ex24_신기한소수 {
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        DFS(2,1);
        DFS(3,1);
        DFS(5,1);
        DFS(7,1);
    }

    private static void DFS(int number, int digit) {
        if(digit == N){
            if(isPrime(number)) System.out.println(number);
            return;
        }

        for(int i = 1; i <= 9; i += 2){
            if(isPrime(number*10 + i)) DFS(number*10 + i, digit + 1);
        }
    }

    private static boolean isPrime(int number) {
        for(int i = 2; i <= number/2; i++){
            if(number % i == 0) return false;
        }
        return true;
    }
}
