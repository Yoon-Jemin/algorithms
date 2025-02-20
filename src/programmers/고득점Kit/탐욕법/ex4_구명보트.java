package programmers.고득점Kit.탐욕법;

import java.util.*;

public class ex4_구명보트 {

    public static void main(String[] args) {
        int[] people = {80, 60, 40, 50, 50, 90, 100, 70};
        int limit = 200;

        System.out.println(solution(people, limit));
    }
    public static int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);

        Deque<Integer> deque = new ArrayDeque<>();
        for(int p : people) deque.add(p);

        while (!deque.isEmpty()){
            int weightSum = 0;
            weightSum += deque.pollLast();
            if (!deque.isEmpty() && weightSum + deque.peekFirst() <= limit){
                weightSum += deque.pollFirst();
            }
            answer++;
        }

        return answer;
    }
}
