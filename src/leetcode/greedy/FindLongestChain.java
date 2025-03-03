package leetcode.greedy;

import java.util.*;

public class FindLongestChain {

    public static void main(String[] args) {
        int[][] pairs = {{1,2},{7,8},{4,5}};
        System.out.println(findLongestChain(pairs));
    }

    public static int findLongestChain(int[][] pairs) {
        List<int[]> chain = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));  // 1 번째 원소 기준으로 오름차순
        for (int[] pair : pairs) {
            pq.offer(pair);
        }

        chain.add(pq.poll());
        while (!pq.isEmpty()) {
            int[] lastChain = chain.get(chain.size() - 1);
            if (lastChain[1] < pq.peek()[0]) chain.add(pq.peek());
            pq.poll();
        }

        return chain.size();
    }
}
