package leetcode.dp;

import java.util.Arrays;

public class LengthOfLIS {

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,2,3};
        System.out.println(lengthOfLIS(nums));
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int max = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
