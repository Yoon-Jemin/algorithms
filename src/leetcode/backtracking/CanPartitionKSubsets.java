package leetcode.backtracking;

import java.util.Arrays;

public class CanPartitionKSubsets {

    public static void main(String[] args) {
        int[] nums = {4,3,2,3,5,2,1};
        int k = 4;
        System.out.println(canPartitionKSubsets(nums, k));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = Arrays.stream(nums).sum();

        if (totalSum % k != 0) return false;

        int targetSum = totalSum / k;
        boolean[] visited = new boolean[nums.length];

        Arrays.sort(nums);
        reverse(nums);

        return backTrack(nums, visited, 0, 0, k, targetSum);
    }

    private static boolean backTrack(int[] nums, boolean[] visited, int startIdx, int currentSum, int k, int targetSum) {
        if (k == 1) return true;

        if (currentSum == targetSum) {
            return backTrack(nums, visited, 0, 0, k - 1, targetSum);
        }

        for (int i = startIdx; i < nums.length; i++) {
            if (visited[i]) continue;
            if (currentSum + nums[i] > targetSum) continue;

            visited[i] = true;
            if (backTrack(nums, visited, i + 1, currentSum + nums[i], k, targetSum)) return true;
            visited[i] = false;
        }

        return false;
    }

    private static void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
