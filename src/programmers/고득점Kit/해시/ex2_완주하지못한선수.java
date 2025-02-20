package programmers.고득점Kit.해시;

import java.util.HashMap;

public class ex2_완주하지못한선수 {

    public String solution(String[] participant, String[] completion) {

        String answer = "";

        HashMap<String,Integer> map = new HashMap<>();

        for(String name : participant){
            if(map.containsKey(name)) {
                int num = map.get(name) + 1;
                map.put(name, num);
            } else {
                map.put(name, 1);
            }
        }

        for(String name : completion){
            if(map.get(name) > 1){
                int num = map.get(name) - 1;
                map.put(name, num);
            } else {
                map.remove(name);
            }
        }

        for(String key: map.keySet()){
            if(map.get(key) == 1) answer = key;
        }

        return answer;
    }
}
