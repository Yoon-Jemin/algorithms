package programmers.level_2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 롤케이크자르기 {

    public static void main(String[] args) {
        int[] topping = {1, 2, 3, 1, 4};
        System.out.println(solution(topping));
    }

    public static int solution(int[] topping) {
        int answer = 0;

        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();


        for (int i = 0; i < topping.length; i++) {
            if(right.containsKey(topping[i])) {
                right.put(topping[i], right.get(topping[i]) + 1);
            } else {
                right.put(topping[i], 1);
            }
        }

        for (int i = 0; i < topping.length; i++) {
            if(left.containsKey(topping[i])) {
                left.put(topping[i], left.get(topping[i]) - 1);
            } else {
                left.put(topping[i], 1);
            }

            if (right.get(topping[i]) == 1) {
                right.remove(topping[i]);
            } else {
                right.put(topping[i], right.get(topping[i]) - 1);
            }

            if (left.size() == right.size()) answer++;
        }

        return answer;
    }
}
