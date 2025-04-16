package leetcode.backtracking;

import java.util.*;

public class Subsets2 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        System.out.println(subsetsWithDup(nums));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backTrack(0, new ArrayList<>(), nums, res);
        return new ArrayList<>(res);
    }

    private static void backTrack(int index, List<Integer> list, int[] nums, List<List<Integer>> res) {
        res.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            backTrack(i + 1, list, nums, res);
            list.remove(list.size() - 1);
        }
    }
}
