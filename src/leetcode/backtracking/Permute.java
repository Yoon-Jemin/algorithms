package leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permute {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(permute(nums));
    }

    public static List<List<Integer>> permute(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        DFS(0, nums, new HashSet<>(), new ArrayList<>(), result);
        return new ArrayList<>(result);
    }

    private static void DFS(int index, int[] nums, Set<Integer> visited, List<Integer> list, Set<List<Integer>> result) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (visited.contains(nums[j])) continue;
                visited.add(nums[j]);
                list.add(nums[j]);
                DFS(i + 1, nums, visited, list, result);
                list.remove(list.size() - 1);
                visited.remove(nums[j]);
            }
        }
    }
}
