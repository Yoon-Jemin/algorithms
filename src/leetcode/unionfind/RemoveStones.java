package leetcode.unionfind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RemoveStones {

    public static void main(String[] args) {
        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        System.out.println(removeStones(stones));
    }

    public static int removeStones(int[][] stones) {
        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> size = new HashMap<>();

        for (int[] stone : stones) {
            int row = stone[0];
            int col = stone[1] + 100000;

            if (!parent.containsKey(row)) {
                parent.put(row, row);
                size.put(row, 1);
            }

            if (!size.containsKey(col)) {
                parent.put(col, col);
                size.put(col, 1);
            }

            union(row, col, parent, size);
        }

        Set<Integer> components = new HashSet<>();
        for (int key : parent.keySet()) {
            components.add(find(key, parent));
        }

        return stones.length - components.size();
    }

    public static void union(int row, int col, Map<Integer, Integer> parent, Map<Integer, Integer> size) {
        int rootRow = find(row, parent);
        int rootCol = find(col, parent);

        if (rootRow != rootCol) {
            if (size.get(rootRow) < size.get(rootCol)) {
                parent.put(rootRow, rootCol);
            } else if (size.get(rootRow) > size.get(rootCol)) {
                parent.put(rootCol, rootRow);
            } else {
                parent.put(rootRow, rootCol);
                size.put(rootCol, size.get(rootCol) + 1);
            }
        }
    }

    public static int find(int x, Map<Integer, Integer> parent) {
        if (parent.get(x) != x) {
            parent.put(x, find(parent.get(x), parent));
        }
        return parent.get(x);
    }
}
