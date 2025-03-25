package programmers.level_2;

import java.util.*;

public class 영어끝말잇기 {

    public int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int turn = i % n;
            // System.out.println("turn = " + (turn + 1));
            // System.out.println("word = " + word);
            if (i > 0 && !word.substring(0,1).equals(words[i-1].substring(words[i-1].length() - 1))) {
                return new int[] {turn + 1, (i + 1) % n == 0 ? (i + 1) / n : (i + 1) / n + 1};
            }

            if (set.contains(word)) {
                return new int[] {turn + 1, (i + 1) % n == 0 ? (i + 1) / n : (i + 1) / n + 1};
            } else {
                set.add(word);
            }
        }

        return new int[] {0, 0};
    }
}
