package leetcode.unionfind;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutive {

    public static void main(String[] args) {
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Map<Integer, Integer> parent = new HashMap<>();
        Map<Integer, Integer> size = new HashMap<>();

        for (int num : nums) {
            if (!parent.containsKey(num)) {
                parent.put(num, num);
                size.put(num, 1);
            }
        }

        for (int num : nums) {
            if (parent.containsKey(num - 1)) {
                union(parent, size, num, num - 1);
            }
            if (parent.containsKey(num + 1)) {
                union(parent, size, num, num + 1);
            }
        }

        int max = 0;
        for (int value : size.values()) {
            max = Math.max(max, value);
        }

        return max;
    }

    private static void union(Map<Integer, Integer> parent, Map<Integer, Integer> size, int num1, int num2) {
        int root1 = find(parent, num1);
        int root2 = find(parent, num2);

        if (root1 != root2) {
            parent.put(root1, root2);
            size.put(root2, size.get(root1) + size.get(root2));
        }
    }

    private static int find(Map<Integer, Integer> parent, int num) {
        if (parent.get(num) != num) {
            parent.put(num, find(parent, parent.get(num)));
        }

        return parent.get(num);
    }
}
