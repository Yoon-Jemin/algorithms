package leetcode.array;

import java.util.Arrays;

public class ThreeSumClosest {

    public static void main(String[] args) {
        int[] nums = {4,0,5,-5,3,3,0,-4,-5};
        int target = -2;
        System.out.println(threeSumClosest(nums, target));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        int closestTarget = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if ( Math.abs(sum - target) <= closest ) {
                    closestTarget = sum;
                    closest = Math.abs(sum - target);
                }


                if (sum < target) left++;
                else if (target < sum) right--;
                else return sum;
            }

        }

        return closestTarget;
    }
}
