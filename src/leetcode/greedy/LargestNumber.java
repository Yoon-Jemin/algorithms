package leetcode.greedy;

import java.util.Arrays;

public class LargestNumber {

    public static void main(String[] args) {
        int[] nums = {10,2,9,39,17};
        System.out.println(largestNumber(nums));    // "9534330"
    }

    public static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        if (strs[0].equals("0")) return "0";
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }

        return sb.toString();
    }
}
