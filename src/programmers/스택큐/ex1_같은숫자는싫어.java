package programmers.스택큐;
import java.util.*;

public class ex1_같은숫자는싫어 {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();

        for(int num : arr){
            if(stack.isEmpty()) stack.push(num);
            if(num != stack.peek()){
                stack.push(num);
            }
        }

        int[] answer = new int [stack.size()];

        for(int i = answer.length - 1; i >= 0; i--){
            answer[i] = stack.pop();
        }

        return answer;
    }
}
