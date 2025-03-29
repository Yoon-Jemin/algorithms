package leetcode.backtracking;

import java.util.*;

public class CombinationSum2 {

    public static void main(String[] args) {
        int[] candidates = {2,5,2,1,2};
        int target = 5;
        System.out.println(combinationSum2(candidates, target));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(candidates);
        DFS(0, new ArrayList<>(), 0, candidates, target, res);
        return new ArrayList<>(res);
    }

    private static void DFS(int sum, List<Integer> list, int index, int[] candidates, int target, List<List<Integer>> res) {
        if (sum == target) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (sum + candidates[i] > target) continue;
            if (i > index && candidates[i] == candidates[i - 1]) continue;

            list.add(candidates[i]);
            sum += candidates[i];
            DFS(sum, list, i + 1, candidates, target, res);
            list.remove(list.size() - 1);
            sum -= candidates[i];
        }
    }
}
