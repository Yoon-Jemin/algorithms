package leetcode.backtracking;

public class FindTargetSumWays {

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int target = 3;
        System.out.println(findTargetSumWays(nums, target));
    }

    public static int findTargetSumWays(int[] nums, int target) {
        return DFS(0, 0, nums, target);
    }

    public static int DFS(int now, int index, int[] nums, int target) {
        if (index == nums.length) {
            if (now == target) return 1;
            return 0;
        }

        return DFS(now + nums[index], index + 1, nums, target) +
                DFS(now - nums[index], index + 1, nums, target);
    }
}
