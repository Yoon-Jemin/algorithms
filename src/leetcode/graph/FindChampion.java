package leetcode.graph;

import java.util.ArrayList;

public class FindChampion {
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = new int[][]{{0, 2}, {1, 3}, {1, 2}};

        System.out.println(findChampion(n, edges));
    }

    public static int findChampion(int n, int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[1]].add(edge[0]);
        }

        int count = 0;
        int winner = -1;
        for (int i = 0; i < n; i++) {
            if (graph[i].size() == 0) {
                winner = i;
                count++;
            }
            if (count > 1) return -1;
        }

        return winner;
    }
}
