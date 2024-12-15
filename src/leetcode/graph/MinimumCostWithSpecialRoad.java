package leetcode.graph;

import java.util.*;

public class MinimumCostWithSpecialRoad {
    public static void main(String[] args) {
        int[] start = {1, 1};
        int[] target = {8, 3};
        int[][] specialRoads = {{2,3,8,2,3}, {1,1,7,3,3}};
        System.out.println(minimumCost(start, target, specialRoads));
    }

    public static int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        Set<String> visited = new HashSet<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        queue.add(new int[]{start[0], start[1], 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int now_X = now[0];
            int now_Y = now[1];
            int cost = now[2];
            String position = now_X + "," + now_Y;

            if(visited.contains(position)) continue;

            if (now_X == target[0] && now_Y == target[1]) {
                return cost;
            }

            visited.add(position);

            queue.add(new int[]{target[0], target[1], cost + Math.abs(now_X - target[0]) + Math.abs(now_Y - target[1])});

            for (int[] specialRoad : specialRoads) {
                if (!visited.contains(specialRoad[2] + "," + specialRoad[3])) {
                    queue.add(new int[]{specialRoad[2], specialRoad[3], Math.abs(now_X - specialRoad[0]) + Math.abs(now_Y - specialRoad[1]) + cost + specialRoad[4]
                    });
                }
            }
        }

        return -1;
    }

}
