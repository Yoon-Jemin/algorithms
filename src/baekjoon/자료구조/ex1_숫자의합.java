package baekjoon.자료구조;

import java.util.Scanner;

// 백준 11720
public class ex1_숫자의합 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();;
        String sNum = sc.next();

        // 입력 받은 String형을 Char형으로 반환
        char cNum[] = sNum.toCharArray();
        int sum = 0;

        for(int i = 0; i < cNum.length; i++){
            sum += cNum[i] - '0';
        }

        System.out.println(sum);
    }
}
