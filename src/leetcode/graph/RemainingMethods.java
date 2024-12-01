package leetcode.graph;

import java.util.*;

public class RemainingMethods {

    public static void main(String[] args) {
        int n = 5;
        int k = 0;
        int[][] invocations = {{1,2},{0,2},{0,1},{3,4}};
        List<Integer> answer = remainingMethods(n, k, invocations);
        System.out.println(answer);
    }

    public static List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        ArrayList<Integer>[] graph1 = new ArrayList[n];     // 가리키는 그래프
        ArrayList<Integer>[] graph2 = new ArrayList[n];     // 가리켜지는 그래프
        List<Integer> defaultAnswer = new ArrayList<>();
        Set<Integer> answer = new HashSet<>();

        for (int i = 0; i < n; i++) {
            graph1[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
            defaultAnswer.add(i);
            answer.add(i);
        }

        for (int[] invocation : invocations) {
            graph1[invocation[0]].add(invocation[1]);
            graph2[invocation[1]].add(invocation[0]);
        }

        boolean[] suspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(k);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            suspicious[cur] = true;

            for (int next : graph1[cur]) {
                if (!suspicious[next]) {
                    queue.add(next);
                }
            }
        }

        int countRemove = 0;
        int countSuspicious = 0;
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) continue;
            countSuspicious++;
            boolean remove = true;
            for (int method : graph2[i]) {
                if (!suspicious[method]) {
                    remove = false;
                    break;
                }
            }
            if (remove) {
                answer.remove(i);
                countRemove++;
            }
        }

        if (countRemove == countSuspicious) {
            return answer.stream().toList();
        }

        return defaultAnswer;
    }
}
