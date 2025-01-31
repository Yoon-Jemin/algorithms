package leetcode.dp;

import java.util.Arrays;

public class CanJump {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
//        int[] nums = {2,3,1,1,4};
        System.out.println(jump(nums));
    }

    public static int jump(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return 0;
        int n = nums.length;    // 5
        int goal = n - 1;

        int[] dp = new int[n - 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);

        for (int i = dp.length - 1; i >= 0; i--) {
            int jump = nums[i];
            if (i + jump >= goal) {
                dp[i] = 1;
                continue;
            }
            for (int j = 1; j <= jump; j++) {
                dp[i] = Math.min(dp[i], dp[i + j] + 1);
            }
        }

        return dp[0];
    }
}
