package programmers.고득점Kit.힙;
import java.util.*;
public class ex1_더맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int num : scoville) queue.add(num);

        while (queue.peek() < K){
            int food1 = queue.poll();
            int food2 = queue.poll();
            int newFood = food1 + food2*2;
            queue.offer(newFood);
            answer++;

            if(queue.size() == 1 && queue.peek() < K){
                answer = -1;
                break;
            }
        }

        return answer;
    }
}
