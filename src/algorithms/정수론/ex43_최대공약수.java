package algorithms.정수론;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

// 백준 P1850
public class ex43_최대공약수 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long a = sc.nextLong();
        long b = sc.nextLong();

        long result = gcd(a,b);

        while (result > 0){
            bw.write("1");
            result--;
        }

        bw.flush();
        bw.close();
    }

    private static long gcd(long num1, long num2) {     // num1: 작은 수, num2: 큰 수
        if(num2 == 0) return num1;
        else return gcd(num2, num1 % num2);
    }
}
