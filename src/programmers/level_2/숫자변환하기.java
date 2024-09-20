package programmers.level_2;

import java.util.*;

public class 숫자변환하기 {

    public static void main(String[] args) {
        System.out.println(solution(10, 40, 30));
    }

    public static int solution(int x, int y, int n) {
        int answer = 0;

        Map<Integer,Set<Integer>> map = new HashMap<>();
        Set<Integer> initialSet = new HashSet<>();
        initialSet.add(x);
        map.put(answer, initialSet);

        if(initialSet.contains(y)) return answer;

        boolean isAvaliable = false;
        while (true) {
            isAvaliable = false;
            Set<Integer> newSet = new HashSet<>();
            Set<Integer> previousSet = map.get(answer);

            for(int num : previousSet) {
                int num1 = num * 2;
                int num2 = num * 3;
                int num3 = num + n;
                newSet.add(num1);
                newSet.add(num2);
                newSet.add(num3);
            }

            answer++;
            map.put(answer, newSet);
            for(int num : newSet){
                if(num <= y) isAvaliable = true;
            }
            if(newSet.contains(y)) break;
            if(!isAvaliable) break;
        }

        if(!isAvaliable) return -1;

        return answer;
    }
}
