package leetcode.backtracking;

import java.util.*;

public class Subsets2 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println(subsetsWithDup(nums));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        backTrack(0, new ArrayList<>(), nums, res);
        return new ArrayList<>(res);
    }

    private static void backTrack(int index, List<Integer> list, int[] nums, Set<List<Integer>> res) {
        res.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            backTrack(i + 1, list, nums, res);
            list.remove(list.size() - 1);
        }
    }
}
