package programmers.level_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 보석쇼핑 {

    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        int[] result = solution(gems);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    public static int[] solution(String[] gems) {
        int[] answer = new int[2];

        Set<String> set = new HashSet<>();
        for (String gem : gems) {
            set.add(gem);
        }

        Map<String, Integer> map = new HashMap<>();
        int start = 0;
        int minLength = Integer.MAX_VALUE;
        for (int end = 0; end < gems.length; end++) {
            String gem = gems[end];

            map.put(gem, map.getOrDefault(gem, 0) + 1);
            while (map.get(gems[start]) > 1) {
                map.put(gems[start], map.get(gems[start]) -1);
                start++;
            }

            if (map.size() == set.size() && end - start < minLength) {
                answer[0] = start + 1;
                answer[1] = end + 1;
                minLength = end - start;
            }
        }
        return answer;
    }
}
