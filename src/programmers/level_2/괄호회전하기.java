package programmers.level_2;

import java.util.*;

public class 괄호회전하기 {
    public int solution(String s) {
        int answer = 0;
        int n = s.length();

        if (n == 1) return 0;

        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(s.substring(i, i+1));
        }

        for (int i = 0; i < n - 1; i++) {
            Boolean isGood = true;
            Stack<String> stack = new Stack<>();
            ArrayList<String> list = new ArrayList<>(queue);

            // for (int j = 0; j < n; j++) System.out.print(list.get(j));

            for (int j = 0; j < n; j++) {
                if (list.get(j).equals("(") || list.get(j).equals("{") || list.get(j).equals("[")) {
                    stack.push(list.get(j));
                } else {    // 닫는 괄호
                    if (stack.isEmpty()) {
                        isGood = false;
                        break;
                    }
                    if (list.get(j).equals(")")) {
                        if (!stack.peek().equals("(")) {
                            isGood = false;
                            break;
                        }
                    } else if (list.get(j).equals("}")) {
                        if (!stack.peek().equals("{")) {
                            isGood = false;
                            break;
                        }
                    } else if (list.get(j).equals("]")) {
                        if (!stack.peek().equals("[")) {
                            isGood = false;
                            break;
                        }
                    }
                    stack.pop();
                }
            }

            if (!stack.isEmpty()) isGood = false;
            if (isGood) answer++;
            queue.add(queue.poll());
        }

        return answer;
    }
}
