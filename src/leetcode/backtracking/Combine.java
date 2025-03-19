package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combine {

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        System.out.println(combine(n, k));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        DFS(1, new ArrayList<Integer>(), n, k, res);
        return res;
    }

    private static void DFS(int start, List<Integer> list, int n, int k, List<List<Integer>> res) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i <= n; i++) {
            list.add(i);
            DFS(i + 1, list, n, k, res);
            list.remove(list.size() - 1);
        }
    }
}
