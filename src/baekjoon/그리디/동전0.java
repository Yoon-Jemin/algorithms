package baekjoon.그리디;

import java.util.Scanner;
import java.util.Stack;

public class 동전0 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int total = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(sc.nextInt());
        }

        int answer = 0;
        while (total > 0) {
            if (stack.peek() > total) {
                stack.pop();
                continue;
            }

            int coin  = stack.pop();
            int count = total / coin;
            answer += count;
            total = total % coin;
        }

        System.out.println(answer);
    }
}
