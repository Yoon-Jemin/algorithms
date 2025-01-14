package leetcode.graph;

import java.util.*;

public class FindSmallestSetOfVertices {

    public static void main(String[] args) {
        int n = 6;
        List<List<Integer>> edges = List.of(List.of(0,1), List.of(0,2), List.of(2,5), List.of(3,4), List.of(4, 2));
        System.out.println(findSmallestSetOfVertices(n, edges));
    }

    public static List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] count = new int[n];
        for (List<Integer> edge : edges) {
            count[edge.get(1)]++;
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (count[i] != 0) continue;
            res.add(i);
        }

        return res;
    }
}
