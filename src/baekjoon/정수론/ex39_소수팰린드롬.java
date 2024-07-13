package baekjoon.정수론;

import java.util.Scanner;

// 백준 P1747
public class ex39_소수팰린드롬 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[10000001];

        for(int i = 2; i < A.length; i++){
            A[i] = i;
        }

        for(int i = 2; i < Math.sqrt(A.length); i++){
            if(A[i] == 0) continue;
            for(int j = i + i; j < A.length; j += i){
                A[j] = 0;
            }
        }

        int i = N;
        while (true){
            if(A[i] != 0){
                int num = A[i];
                if(isPelindrome(num)){
                    System.out.println(num);
                    break;
                }
            }
            i++;
        }
    }

    private static boolean isPelindrome(int num) {
        char temp[] = String.valueOf(num).toCharArray();
        int start = 0;
        int end = temp.length -1;

        while(start < end){
            if(temp[start] != temp[end]){
                return false;
            }
            start++; end--;
        }
        return true;
    }
}
