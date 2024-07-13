package algorithms.정수론;

import java.io.IOException;
import java.util.Scanner;

// 백준 P1934
public class ex42_최소공배수 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int testNum = sc.nextInt();   // 테스트 케이스 개수

        for(int i = 0; i < testNum; i++){
            int A = sc.nextInt();
            int B = sc.nextInt();   // B가 더 큰 값
            int result = gcd(A,B);
            System.out.println(A * B / result);
        }

    }

    private static int gcd(int a, int b) {
        if( b % a == 0) return a;
        else return gcd(b % a, b);
    }

}

// A,B의 최소공배수 -> A * B / 최대공약수
