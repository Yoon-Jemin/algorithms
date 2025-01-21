package leetcode.greedy;

public class CanJump {

    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
//        int[] nums = {2,0};
        System.out.println(canJump(nums));
    }

    public static boolean canJump(int[] nums) {
        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) return true;
        }

        return false;
    }
}
