package programmers.level_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 압축 {

    public static void main(String[] args) {
        int[] answer = solution("ABABABABABABABAB");
        for (int a : answer) System.out.print(a + " ");
    }

    public static int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        preProcess(map);

        int count = 27;
        List<Integer> answer = new ArrayList<>();

        int idx = 0;
        while (idx < msg.length()) {
            String w = String.valueOf(msg.charAt(idx));

            while (idx + 1 < msg.length()) {
                w += String.valueOf(msg.charAt(idx + 1));

                if (map.containsKey(w)) idx++;
                else break;
            }

            if (idx == msg.length() - 1) {
                answer.add(map.get(w));
                break;
            }

            String w_c = w;
            w = w.substring(0, w.length() - 1);
            answer.add(map.get(w));

            if (!w.equals(w_c)) {
                map.put(w_c, count);
                count++;
            }

            idx++;
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static void preProcess(Map<String, Integer> map) {
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        map.put("E", 5);
        map.put("F", 6);
        map.put("G", 7);
        map.put("H", 8);
        map.put("I", 9);
        map.put("J", 10);
        map.put("K", 11);
        map.put("L", 12);
        map.put("M", 13);
        map.put("N", 14);
        map.put("O", 15);
        map.put("P", 16);
        map.put("Q", 17);
        map.put("R", 18);
        map.put("S", 19);
        map.put("T", 20);
        map.put("U", 21);
        map.put("V", 22);
        map.put("W", 23);
        map.put("X", 24);
        map.put("Y", 25);
        map.put("Z", 26);
    }
}
