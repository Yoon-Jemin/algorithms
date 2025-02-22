package leetcode.graph;

import java.util.*;

public class IsPrintable {

    public static void main(String[] args) {
        int[][] targetGrid = {
                {1,1,1,1},
                {1,1,3,3},
                {1,1,3,4},
                {5,5,1,4}
        };
        System.out.println(isPrintable(targetGrid));
    }

    public static boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length;
        int n = targetGrid[0].length;

        Map<Integer, int[]> colorBounds = new HashMap<>();
        Set<Integer> colors = new HashSet<>();

        for (int r = 0 ; r < m ; r++) {
            for (int c = 0 ; c < n ; c++) {
                int color = targetGrid[r][c];
                colors.add(color);
                colorBounds.putIfAbsent(color, new int[]{r, r, c, c});
                int[] bounds = colorBounds.get(color);
                bounds[0] = Math.min(bounds[0], r);
                bounds[1] = Math.max(bounds[1], r);
                bounds[2] = Math.min(bounds[2], c);
                bounds[3] = Math.max(bounds[3], c);
            }
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (int color : colors) {
            graph.put(color, new HashSet<>());
            inDegree.put(color, 0);
        }

        for (int color : colors) {
            int[] bounds = colorBounds.get(color);
            for (int r =bounds[0]; r <= bounds[1]; r++) {
                for (int c =bounds[2]; c <= bounds[3]; c++) {
                    int insideColor = targetGrid[r][c];
                    if (insideColor != color) {
                        if (!graph.get(color).contains(insideColor)) {
                            graph.get(color).add(insideColor);
                            inDegree.put(insideColor, inDegree.get(insideColor) + 1);
                        }
                    }
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int color : colors) {
            if (inDegree.get(color) == 0) queue.add(color);
        }

        int processed = 0;
        while (!queue.isEmpty()) {
            int color = queue.poll();
            processed++;
            for (int neighbor : graph.get(color)) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        return processed == colors.size();
    }
}
