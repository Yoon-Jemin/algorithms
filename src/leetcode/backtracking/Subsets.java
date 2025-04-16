package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backTrack(0, new ArrayList<>(), nums, res);
        return res;
    }

    private static void backTrack(int index, List<Integer> list, int[] nums, List<List<Integer>> res) {
        res.add(new ArrayList<>(list));

        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            backTrack(i + 1, list, nums, res);
            list.remove(list.size() - 1);
        }
    }
}
