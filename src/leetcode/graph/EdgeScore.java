package leetcode.graph;

import java.util.ArrayList;

public class EdgeScore {

    public static void main(String[] args) {
        int[] edges = {1,0,0,0,0,7,7,5};
        System.out.println(edgeScore(edges));
    }

    public static int edgeScore(int[] edges) {

        ArrayList<Integer>[] graph = new ArrayList[edges.length];
        for (int i = 0; i < edges.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++) {
            int from = i;
            int to = edges[i];
            graph[to].add(from);
        }

        int answer = -1;
        long maxSum = 0;
        for (int i = 0; i < edges.length; i++) {
            long sum = 0;
            for (int j = 0; j < graph[i].size(); j++) {
                sum += graph[i].get(j);
            }
            if (sum > maxSum) {
                maxSum = sum;
                answer = i;
            }
        }

        return answer;
    }
}
