package programmers.완전탐색;

import java.util.Arrays;
import java.util.HashMap;

public class ex7_모음사전 {
    public static void main(String[] args) {

        String word = "EIO";
        System.out.println(solution(word));
    }

    static int cnt = 1;
    static HashMap<String, Integer> map = new HashMap<>();
    public static int solution(String word) {
        init(0, "");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++){
            char target = word.charAt(i);
            if(target == 'A') sb.append(0);
            if(target == 'E') sb.append(1);
            if(target == 'I') sb.append(2);
            if(target == 'O') sb.append(3);
            if(target == 'U') sb.append(4);
        }
        String key = sb.toString();
        return map.get(key);
    }

    private static void init(int depth, String cur) {
        if(depth == 5) return;

        for (int i = 0; i < 5; i++){
            String next = cur + Integer.toString(i);
            map.put(next, cnt);
            cnt++;
            init(depth + 1, next);
        }
    }
    // init(0,0) init(1, 0) -> init(2, 00) -> init(3, 000) -> init(4, 0000) -> init(5, 00000) (X)
    //
}
