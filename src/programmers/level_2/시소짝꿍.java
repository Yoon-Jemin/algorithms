package programmers.level_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class 시소짝꿍 {

    public static void main(String[] args) {
        int[] weights = {100,180,360,100,270};
        System.out.println(solution(weights));
    }

//    {100, 100} 은 같은 거리
//    {180, 360} 은 각각 4(m), 2(m) 거리
//    {180, 270} 은 각각 3(m), 2(m) 거리
//    {270, 360} 은 각각 4(m), 3(m) 거리

    public static long solution(int[] weights) {
        long answer = 0;

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        for(int i = 0; i < weights.length; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(weights[i]*2);
            list.add(weights[i]*3);
            list.add(weights[i]*4);
            map.put(i, list);
        }

        for(int i = 0; i < weights.length; i++) {
            for(int j = i + 1; j < weights.length; j++) {
                for(int k = 0; k < 3; k++){
                    if(map.get(j).contains(map.get(i).get(k))) {
                        answer++;
                        break;
                    }
                }
            }
        }

        return answer;
    }
}
