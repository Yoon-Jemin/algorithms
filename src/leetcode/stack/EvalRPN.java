package leetcode.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EvalRPN {

    public static void main(String[] args) {
        String[] tokens = {"4","13","5","/","+"};
        System.out.println(evalRPN(tokens));
    }

    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();

        Set<String> set = new HashSet<>();
        set.add("+");
        set.add("-");
        set.add("*");
        set.add("/");

        for (String token : tokens) {
            if (set.contains(token)) {  // 연산자
                String num1 = stack.pop();
                String num2 = stack.pop();
                stack.push(calculate(num2, num1, token));
            } else {    // 피연산자
                stack.push(token);
            }
        }

        return Integer.parseInt(stack.pop());
    }

    private static String calculate(String num1, String num2, String token) {
        int intNum1 = Integer.parseInt(num1);
        int intNum2 = Integer.parseInt(num2);
        int result = 0;

        if (token.equals("+")) {
            result = intNum1 + intNum2;
        } else if (token.equals("-")) {
            result = intNum1 - intNum2;
        } else if (token.equals("*")) {
            result = intNum1 * intNum2;
        } else if (token.equals("/")) {
            result = intNum1 / intNum2;
        }
        return String.valueOf(result);
    }
}
