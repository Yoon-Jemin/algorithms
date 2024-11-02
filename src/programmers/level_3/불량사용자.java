package programmers.level_3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class 불량사용자 {

    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
        System.out.println(solution(user_id, banned_id));
    }

    static ArrayList<Set<String>> list = new ArrayList<>();
    static String[] user;
    static String[] banned;
    static int answer = 0;
    static boolean[] visited;
    static HashSet<String> set = new HashSet<>();
    public static int solution(String[] user_id, String[] banned_id) {

        user = user_id;
        banned = banned_id;
        visited = new boolean[banned_id.length];

        for(String cur : user) {
            Set<String> set = new HashSet<>();
            int cur_length = cur.length();
            for(String next: banned) {
                if(cur_length != next.length()) continue;
                boolean flag = true;
                for(int i = 0; i < cur_length; i++) {
                    if(next.charAt(i) == '*') continue;
                    if(next.charAt(i) != cur.charAt(i)) {
                        flag = false;
                        break;
                    }
                }
                if(flag) set.add(next);
            }
            list.add(set);
        }

        find(0, new ArrayList<>());
        return answer;
    }

    private static void find(int depth, ArrayList<Integer> check) {
        if(depth == user.length){
            boolean flag = true;
            for(int i = 0; i < banned.length; i++) {
                if(!visited[i]) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                StringBuilder sb = new StringBuilder();
                for(int next : check) {
                    sb.append(next);
                }
                String result = sb.toString();
                if(!set.contains(result)) {
                    set.add(result);
                    answer++;
                }
            }
            return;
        }

        for(int i = 0; i < banned.length; i++) {
            if(visited[i]) continue;
            if(list.get(depth).contains(banned[i])) {
                visited[i] = true;
                ArrayList<Integer> temp = new ArrayList<>(check);
                temp.add(depth);
                find(depth + 1, temp);
                visited[i] = false;
            }
        }
        find(depth + 1, new ArrayList<>(check));
    }
}
