package programmers.고득점Kit.스택큐;
import java.util.*;

public class ex6_주식가격 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Queue<Integer> queue = new LinkedList<>();

        for(int num: prices) queue.add(num);

        int i = 0;
        while(!queue.isEmpty()){
            int count = 0;
            int firstNum = queue.poll();

            for(int num : queue){
                count++;
                if(num < firstNum) break;
            }

            answer[i] = count;
            i++;
        }

        return answer;
    }
}
