package leetcode.graph;

import java.util.ArrayList;
import java.util.List;

public class MinimumFuelCost {

    public static void main(String[] args) {
//        int[][] roads = {{0,1}, {0,2}, {1,3}, {1,4}};
//        int seats = 5;

        int[][] roads = {{3,1}, {3,2}, {1,0}, {0,4}, {0,5}, {4,6}};
        int seats = 2;

        System.out.println(minimumFuelCost(roads, seats));
    }

    static List<List<Integer>> graph;
    static long fuel;
    public static long minimumFuelCost(int[][] roads, int seats) {
        graph = new ArrayList<>();
        int n = roads.length + 1;
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }

        fuel = 0;
        dfs(0, -1, seats);
        return fuel;
    }

    public static int dfs(int city, int parent, int seats) {
        int representatives = 1;    // 현재 도시의 대표자 수

        for (int neighbor : graph.get(city)) {
            if(neighbor == parent) continue;
            int childRepresentatives = dfs(neighbor, city, seats);
            fuel += (childRepresentatives + seats - 1) / seats;
            representatives += childRepresentatives;
        }

        return representatives;
    }
}
