package leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        System.out.println(combinationSum(candidates, 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Set<List<Integer>> result = new HashSet<>();
        DFS(candidates, target, 0, new ArrayList<>(), result);
        return new ArrayList<>(result);
    }

    private static void DFS(int[] candidates, int target, int index, List<Integer> list, Set<List<Integer>> result) {
        int sum = 0;
        for (int num : list) sum += num;

        if (sum == target) result.add(new ArrayList<>(list));

        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target) continue;
            list.add(candidates[i]);
            DFS(candidates, target, i, list, result);
            list.remove(list.size() - 1);
        }
    }
}
