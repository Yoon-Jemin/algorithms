package leetcode.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxStarSum {

    public static void main(String[] args) {
//        int[] vals= {1,2,3,4,10,-10,-20};
//        int[][] edges = {{0,1},{1,2},{1,3},{3,4},{3,5},{3,6}};
//        int k = 2;
        int[] vals= {-5};
        int[][] edges = {};
        int k = 0;
        System.out.println(maxStarSum(vals, edges, k));
    }

//    vals = [1,2,3,4,10,-10,-20], edges = [[0,1],[1,2],[1,3],[3,4],[3,5],[3,6]], k = 2

    public static int maxStarSum(int[] vals, int[][] edges, int k) {
        PriorityQueue<int[]>[] graph = new PriorityQueue[vals.length];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new PriorityQueue<int[]>((a, b) -> Integer.compare(b[1], a[1]));
        }

        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(new int[]{edges[i][1], vals[edges[i][1]]});
            graph[edges[i][1]].add(new int[]{edges[i][0], vals[edges[i][0]]});
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < graph.length; i++) {
            PriorityQueue<int[]> pq = graph[i];
            int count = vals[i];
            for (int j = 0; j < k; j++) {
                if(pq.isEmpty() || pq.peek()[1] < 0 || j > k) break;
                count += pq.poll()[1];
            }
            max = Math.max(max, count);
        }

        return max;
    }
}
