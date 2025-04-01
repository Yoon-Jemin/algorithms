package leetcode.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumScore {

    public static void main(String[] args) {
        System.out.println(maximumScore(6, 2, 1));
    }

    public static int maximumScore(int a, int b, int c) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.add(a);
        pq.add(b);
        pq.add(c);

        int answer = 0;
        while (pq.size() > 1) {
            int first = pq.poll();
            first--;
            int second = pq.poll();
            second--;
            if (first > 0) pq.add(first);
            if (second > 0) pq.add(second);
            answer++;
        }

        return answer;
    }
}
