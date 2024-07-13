package programmers.해시;

import java.util.HashMap;
import java.util.Map;

public class ex4_의상 {

    public int solution(String[][] clothes) {
        int answer = 0;

        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < clothes.length; i++){
            if(map.containsKey(clothes[i][1])){
                int number = map.get(clothes[i][1]);
                map.put(clothes[i][1], number + 1);
            } else {
                map.put(clothes[i][1], 1);
            }
        }

        int result = 1;
        for(int num: map.values()){
            result *= (num+1);
        }

        answer = result - 1;

        return answer;
    }
}
