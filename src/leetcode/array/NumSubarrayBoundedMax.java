package leetcode.array;

import java.util.*;

public class NumSubarrayBoundedMax {

    public static void main(String[] args) {
//        int[] nums = {7,3,6,7,1};
//        int left = 1;
//        int right = 4;
        int[] nums = {2,9,2,5,6};
        int left = 2;
        int right = 8;
        System.out.println(numSubarrayBoundedMax(nums, left, right));
    }

    public static int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int leftPointer = 0;
        int count = 0;
        int prev = 0;

        for (int rightPointer = 0; rightPointer < nums.length; rightPointer++) {
            if (nums[rightPointer] > right) {
                leftPointer = rightPointer + 1;
                prev = 0;
            } else if (nums[rightPointer] >= left) {
                prev = rightPointer - leftPointer + 1;
                count += prev;
            } else {
                count += prev;
            }
        }
        return count;
    }
}
