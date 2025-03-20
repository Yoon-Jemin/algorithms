package programmers.level_2;

import java.util.*;

public class 방문길이 {

    public int solution(String dirs) {
        int answer = 0;

        Map<String, Set<String>> map = new HashMap<>();
        Map<String, int[]> move = new HashMap<>();
        move.put("U", new int[] {0,1});
        move.put("D", new int[] {0,-1});
        move.put("R", new int[] {1,0});
        move.put("L", new int[] {-1,0});

        int[] now = new int[] {0, 0};
        String nowKey = now[0] + "," + now[1];
        map.put(nowKey, new HashSet<String>());

        for (int i = 0; i < dirs.length(); i++) {
            String dir = dirs.substring(i, i + 1);
            int[] next = new int[2];
            next[0] = now[0] + move.get(dir)[0];
            next[1] = now[1] + move.get(dir)[1];
            String nextKey = next[0] + "," + next[1];

            if (Math.abs(next[0]) > 5 || Math.abs(next[1]) > 5) continue;

            if (!map.get(nowKey).contains(nextKey)) {
                answer++;
                map.get(nowKey).add(nextKey);
                if (!map.containsKey(nextKey)) map.put(nextKey, new HashSet<String>());
                map.get(nextKey).add(nowKey);
            }


            now = next;
            nowKey = next[0] + "," + next[1];
        }

        return answer;
    }
}
