package algorithms.자료구조;

import java.util.Scanner;

// 백준 P2018
public class ex6_연속된자연수의합 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int count = 1; // 자기 자신으로만 이루어지는 경우를 포함
        int start = 1;
        int end = 1;
        int sum = 1;

        while (end != N){
            if(sum == N){
                count++;
                end++;
                sum += end;
            } else if (sum > N) {
                sum -= start;
                start++;
            } else if (sum < N) {
                end++;
                sum += end;
            }
        }

        System.out.println(count);
    }
}
