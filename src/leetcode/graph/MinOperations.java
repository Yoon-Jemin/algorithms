package leetcode.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MinOperations {

    public static void main(String[] args) {
        System.out.println(minOperations(10, 12));
    }

    public static int minOperations(int n, int m) {
        if(isPrime(n)) return -1;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        Set<Integer> visited = new HashSet<>();

        pq.add(new int[]{n, n});
        visited.add(n);

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int num = cur[0];
            int cost = cur[1];

            if (num == m) return cost;

            char[] digits = String.valueOf(num).toCharArray();
            for (int i = 0; i < digits.length; i++) {
                for (int diff : new int[] {-1, 1}) {
                    if (digits[i] == '9' && diff == 1 || digits[i] == '0' && diff == -1) continue;

                    char[] nextDigits = digits.clone();
                    nextDigits[i] += diff;
                    int nextNum = Integer.parseInt(String.valueOf(nextDigits));

                    if (!isPrime(nextNum) && !visited.contains(nextNum)) {
                        pq.add(new int[]{nextNum, cost + nextNum});
                        visited.add(nextNum);
                    }

                }
            }
        }

        return -1;
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i*i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
