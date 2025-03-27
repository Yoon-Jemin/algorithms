package leetcode.greedy;

import java.util.*;

public class LeastInterval {

    public static void main(String[] args) {
        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;
        System.out.println(leastInterval(tasks, n));
    }

    public static int leastInterval(char[] tasks, int n) {
        int cycle = n + 1;

        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : tasks) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int v : countMap.values()) {
            pq.add(v);
        }

        int time = 0;
        while (!pq.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < cycle; i++) {
                if (pq.isEmpty()) break;
                temp.add(pq.poll());
            }

            for (int t : temp) {
                if (t - 1 >= 1) pq.add(t - 1);
            }

            time += !pq.isEmpty() ? cycle : temp.size();
        }

        return time;
    }
}
