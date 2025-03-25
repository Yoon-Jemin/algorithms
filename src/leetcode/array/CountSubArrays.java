package leetcode.array;

public class CountSubArrays {

    public static void main(String[] args) {
        int[] nums = {1,3,2,3,3};
        int k = 2;
        System.out.println(countSubarrays(nums, k));
    }

    public static long countSubarrays(int[] nums, int k) {
        int maxElement = 0;
        int startIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            maxElement = Math.max(maxElement, nums[i]);
        }

        long answer = 0L;
        int count = 0;
        for (int endIndex = 0; endIndex < nums.length; endIndex++) {
            if (nums[endIndex] == maxElement) count++;

            while (count >= k) {
                if (nums[startIndex] == maxElement) count--;
                startIndex++;
            }

            answer += startIndex;
        }

        return answer;
    }
}
