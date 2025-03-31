package leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumsSameConsecDiff {

    public static void main(String[] args) {
        int n = 2;
        int k = 1;
        int[] result = numsSameConsecDiff(n, k);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] numsSameConsecDiff(int n, int k) {
        Set<Integer> res = new HashSet<>();

        int digit = 1;
        for (int i = 1; i <= 9; i++) {
            List<Integer> nums = new ArrayList<>();
            nums.add(i);
            DFS(digit, nums, res, n, k);
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    private static void DFS(int digit, List<Integer> nums, Set<Integer> res, int n, int k) {
        if (digit == n) {
            StringBuilder sb = new StringBuilder();
            for (int num : nums) {
                sb.append(num);
            }
            res.add(Integer.parseInt(sb.toString()));
            return;
        }

        int prevNum = nums.get(nums.size() - 1);
        if (prevNum - k >= 0) {
            int nextNum = prevNum - k;
            nums.add(nextNum);
            DFS(digit + 1, nums, res, n, k);
            nums.remove(nums.size() - 1);
        }
        if (prevNum + k < 10) {
            int nextNum = prevNum + k;
            nums.add(nextNum);
            DFS(digit + 1, nums, res, n, k);
            nums.remove(nums.size() - 1);
        }
    }
}
