package leetcode.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaximumImportance {

    public static void main(String[] args) {
        int n = 5;
        int[][] roads = {{0,1},{1,2},{2,3},{0,2},{1,3},{2,4}};

        System.out.println(maximumImportance(n, roads));
    }

    public static long maximumImportance(int n, int[][] roads) {
        List<int[]> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new int[]{i, 0, 0});
        }

        for (int[] road : roads) {
            graph.get(road[0])[1]++;
            graph.get(road[1])[1]++;
        }

        graph.sort(Comparator.comparingInt(a -> a[1]));

        for (int i = 0; i < graph.size(); i++) {
            graph.get(i)[2] = i + 1;
        }

        long ans = 0;
        for (int[] road : graph) {
            ans += (long) road[1] * road[2];
        }

        return ans;
    }
}
