package programmers.level_3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 야근지수 {

    public static void main(String[] args) {
        System.out.println(solution(4, new int[]{4,3,3}));
    }

//    [4, 3, 3]	4	12
//    [2, 1, 2]	1	6
//    [1,1]	3	0

    public static long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int work : works) {
            pq.add(work);
        }

        for (int i = 0; i < n; i++) {
            if(pq.isEmpty()) break;
            int work = pq.poll();
            work--;
            if(work == 0) continue;
            pq.add(work);
        }

        while (!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }

        return answer;
    }
}
