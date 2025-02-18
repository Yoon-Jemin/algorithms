package leetcode.sorting;

import java.util.Arrays;

public class MinMoves2 {

    public static void main(String[] args) {
        int[] nums = {1,0,0,8,6};
        System.out.println(minMoves2(nums));
    }

    public static int minMoves2(int[] nums) {
        int medium = 0;

        Arrays.sort(nums);
        medium = nums[nums.length / 2];

        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            answer += Math.abs(nums[i] - medium);
        }

        return answer;
    }
}
