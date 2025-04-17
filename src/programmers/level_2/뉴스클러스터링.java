package programmers.level_2;

import java.util.*;

public class 뉴스클러스터링 {

    public static void main(String[] args) {
        String str1 = "aa1+aa2";
        String str2 = "AAAA12";
        System.out.println(solution(str1, str2));
    }

    public static int OFFSET = 65536;
    public static int solution(String str1, String str2) {
        int answer = 0;

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        int union = 0;    // 합집합
        int common = 0;    // 교집합

        for (int i = 0; i < str1.length() - 1; i++) {
            String str = str1.substring(i, i + 2);

            if (isEnglish(str)) {
                str = str.toLowerCase();
                map1.put(str, map1.getOrDefault(str, 0) + 1);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            String str = str2.substring(i, i + 2);
            if (isEnglish(str)) {
                str = str.toLowerCase();
                map2.put(str, map2.getOrDefault(str, 0) + 1);
            }
        }

        for (String key : map2.keySet()) {
            union += map2.get(key);
        }


        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {    // 교집합
                common += Math.min(map1.get(key), map2.get(key));
                if (map2.get(key) < map1.get(key)) {
                    union += map1.get(key) - map2.get(key);
                }
            } else {
                union += map1.get(key);
            }
        }

        if (union == 0) return OFFSET;

        answer = common * OFFSET;
        answer = answer / union;
        return answer;
    }

    private static boolean isEnglish(String str) {
        boolean isEnglish = true;
        for (int i = 0; i < str.length(); i++) {
            String s = str.substring(i, i + 1);
            if (!s.matches("^[a-zA-Z]$")) isEnglish = false;
        }
        return isEnglish;
    }
}
