package programmers.고득점Kit.해시;

import java.util.HashSet;
import java.util.Set;

public class ex1_폰켓몬 {
    public static int solution(int[] nums) {
        int answer = 0;
        int length = nums.length;
        int numPoke = length / 2;

        Set<Integer> setPoke = new HashSet<Integer>();

        for(Integer i : nums){
            setPoke.add(i);
            if (setPoke.size() == numPoke) break;
        }

        answer = setPoke.size();
        return answer;
    }
}
