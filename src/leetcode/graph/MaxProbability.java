package leetcode.graph;

import java.util.*;

public class MaxProbability {

    public static void main(String[] args) {
        int n = 10;
        int[][] edges = {{0,3}, {1,7}, {1,2}, {0,9}};
        double[] succProb = {0.31, 0.9, 0.86, 0.36};
        int start = 2;
        int end = 3;
//        int n = 3;
//        int[][] edges = {{0,1}};
//        double[] succProb = {0.5};
//        int start = 0;
//        int end = 2;

        System.out.println(maxProbability(n, edges, succProb, start, end));
    }

    public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        ArrayList<double[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges.length; i++){
            int[] edge = edges[i];
            graph[edge[0]].add(new double[] {edge[1], succProb[i]});
            graph[edge[1]].add(new double[] {edge[0], succProb[i]});
        }

        double[] maxProb = new double[n];
        maxProb[start_node] = 1;

        PriorityQueue<double[]> pq = new PriorityQueue<>((a,b) -> Double.compare(b[1],a[1]));
        pq.offer(new double[] {start_node, maxProb[start_node]});

        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            double curNode = cur[0];
            double curProb = cur[1];

            if (curNode == end_node) return curProb;

            for (double[] next : graph[(int) curNode]){
                double nextNode = next[0];
                double nextProb = next[1] * curProb;
                if (nextProb > maxProb[(int) nextNode]) {
                    maxProb[(int) nextNode] = nextProb;
                    pq.offer(new double[] {nextNode, nextProb});
                }
            }
        }

        return 0;
    }
}
