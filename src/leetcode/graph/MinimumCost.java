package leetcode.graph;

import java.util.*;

public class MinimumCost {

    public static void main(String[] args) {
//        String source = "abcd";
//        String target = "acbe";
//        char[] original = {'a','b','c','c','e','d'};
//        char[] changed = {'b','c','b','e','b', 'e'};
//        int[] cost = {2,5,5,1,2,20};
        String source = "aaaa";
        String target = "bbbb";
        char[] original = {'a','c'};
        char[] changed = {'c','b'};
        int[] cost = {1, 2};
        System.out.println(minimumCost(source, target, original, changed, cost));
    }

    public static long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        int ALPHABET_SIZE = 26;
        long INF = Long.MAX_VALUE / 2; // To avoid overflow

        long[][] dist = new long[ALPHABET_SIZE][ALPHABET_SIZE];
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for (int i = 0; i < original.length; i++) {
            int from = charToIndex(original[i]);
            int to = charToIndex(changed[i]);
            dist[from][to] = Math.min(dist[from][to], cost[i]);
        }

        for (int k = 0; k < ALPHABET_SIZE; k++) {
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                for (int j = 0; j < ALPHABET_SIZE; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        long totalCost = 0;
        for (int i = 0; i < source.length(); i++) {
            int sIdx = charToIndex(source.charAt(i));
            int tIdx = charToIndex(target.charAt(i));

            if (dist[sIdx][tIdx] == INF) {
                return -1;
            }

            totalCost += dist[sIdx][tIdx];
        }

        return totalCost;
    }

    public static int charToIndex(char c) {
        return c - 'a';
    }
}
