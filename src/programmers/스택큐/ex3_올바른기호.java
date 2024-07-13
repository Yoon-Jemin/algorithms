package programmers.스택큐;
import java.util.*;

public class ex3_올바른기호 {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++){
            Character symbol = s.charAt(i);
            if(stack.isEmpty() || symbol.equals('(')){
                stack.push(symbol);
            }else { // ")" 일 때
                if(stack.peek().equals('(')){
                    stack.pop();
                }
            }
        }

        if(stack.isEmpty()) answer = true;
        else answer = false;

        return answer;
    }
}
