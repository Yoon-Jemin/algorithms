package baekjoon.스택;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class 반복순열 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();

        Set<Integer> set = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        set.add(n);
        stack.push(n);

        int nextNum = getNextNum(n, p);

        while (!set.contains(nextNum)) {
            set.add(nextNum);
            stack.push(nextNum);
            nextNum = getNextNum(nextNum, p);
        }

        while (stack.peek() != nextNum) {
            stack.pop();
        }

        stack.pop();
        System.out.println(stack.size());
    }

    private static int getNextNum(int n, int p) {
        String strNum = String.valueOf(n);

        int result = 0;
        for (int i = 0; i < strNum.length(); i++) {
            int number = Integer.parseInt(strNum.substring(i, i + 1));
            result += Math.pow(number, p);
        }

        return result;
    }
}
