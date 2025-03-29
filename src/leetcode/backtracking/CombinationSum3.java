package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {

    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 7));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        DFS(1,0, new ArrayList<>(), k, n, res);
        return res;
    }

    private static void DFS(int index, int sum, List<Integer> list, int k, int n, List<List<Integer>> res) {
        if (list.size() == k) {
            if (sum == n) res.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i <= 9; i++) {
            if (sum + i > n) break;

            list.add(i);
            sum += i;
            DFS(i + 1, sum, list, k, n, res);
            list.remove(list.size() - 1);
            sum -= i;
        }
    }
}
