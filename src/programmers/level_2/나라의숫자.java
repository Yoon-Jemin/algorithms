package programmers.level_2;

import java.util.*;

public class 나라의숫자 {

    public static void main(String[] args) {
//        for (int i = 1; i < 10; i++) {
//            System.out.println(solution(i));
//        }

        System.out.println(solution(10));
    }

    public static String solution(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(0, 4);

        Stack<Integer> stack = new Stack<>();

        while (n > 0) {
            int remainder = n % 3;
            stack.push(remainder);
            n = (n - 1) / 3;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(map.get(stack.pop()));
        }

        return sb.toString();
    }
}
