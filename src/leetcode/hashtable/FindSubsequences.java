package leetcode.hashtable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindSubsequences {

    public static void main(String[] args) {
        int[] nums = {4,6,7,7};
        System.out.println(findSubsequences(nums));
    }

    public static List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        DFS(nums, 0, new ArrayList<>(), result);
        return new ArrayList<>(result);
    }

    public static void DFS(int[] nums, int index, List<Integer> cur, Set<List<Integer>> result) {
        if (cur.size() >= 2) result.add(new ArrayList<>(cur));

        for (int i = index; i < nums.length; i++) {
            if (cur.isEmpty() || cur.get(cur.size() - 1) <= nums[i]) {
                cur.add(nums[i]);
                DFS(nums, i + 1, cur, result);
                cur.remove(cur.size() - 1);
            }
        }
    }
}
