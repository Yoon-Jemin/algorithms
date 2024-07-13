package baekjoon.자료구조;

import java.util.Scanner;
import java.util.Stack;

// 백준 P1874
public class ex11_스택수열 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];

        for(int i = 0; i < A.length; i++){
            A[i] = sc.nextInt();
        }

        Stack<Integer> stack = new Stack<>();
        StringBuffer bf = new StringBuffer();
        boolean result = true;

        int num = 1;

        for(int i=0; i < N; i++){
            int su = A[i];  // 현재 수열의 값

            if(su >= num){  // 현재 수열의 값이 오름차순 자연수보다 큼
                while(su >= num){
                    stack.push(num++);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
            } else if ( su < num ) {    // 현재 수열의 값이 오름차순 자연수보다 큼
                int n = stack.pop();

                if( n > su ){
                    System.out.println("NO");
                    result = false;
                    break;
                }

                bf.append("-\n");

            }
        }

        if(result) System.out.println(bf.toString());
    }
}
