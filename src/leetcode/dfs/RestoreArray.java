package leetcode.dfs;

import java.util.*;

public class RestoreArray {

    public static void main(String[] args) {
//        [-3,-9],[-5,3],[2,-9],[6,-3],[6,1],[5,3],[8,5],[-5,1],[7,2]
        int[][] adjacentPairs = {{-3,-9}, {-5,3}, {2,-9}, {6,-3}, {6,1}, {5,3}, {8,5}, {-5,1}, {7,2}};
        int[] result = restoreArray(adjacentPairs);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] pair : adjacentPairs) {
            if (map.containsKey(pair[0])) {
                map.get(pair[0]).add(pair[1]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(pair[1]);
                map.put(pair[0], list);
            }
            if (map.containsKey(pair[1])) {
                map.get(pair[1]).add(pair[0]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(pair[0]);
                map.put(pair[1], list);
            }
        }

        int start = 0;
        for (int i : map.keySet()) {
            if (map.get(i).size() == 1) {
                start = i;
                break;
            }
        }

        int n = adjacentPairs.length + 1;
        int[] result = new int[n];
        Set<Integer> visited = new HashSet<>();

        result[0] = start;
        visited.add(start);

        for (int i = 1; i < n; i++) {
            for (int neighbor : map.get(result[i - 1])) {
                if (!visited.contains(neighbor)) {
                    result[i] = neighbor;
                    visited.add(neighbor);
                    break;
                }
            }
        }

        return result;
    }
}
