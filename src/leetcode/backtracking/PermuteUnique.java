package leetcode.backtracking;

import java.util.*;

public class PermuteUnique {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(permuteUnique(nums));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        Boolean[] used = new Boolean[nums.length];
        Arrays.fill(used, false);
        DFS(new ArrayList<>(), nums, used, res);
        return res;
    }

    private static void DFS(List<Integer> list, int[] nums, Boolean[] used, List<List<Integer>> res) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            list.add(nums[i]);
            DFS(list, nums, used, res);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
