package leetcode.bs;

public class SearchRange {

    public static void main(String[] args) {
//        int[] nums = {5,7,7,8,8,10};
//        int target = 8;
        int[] nums = {1};
        int target = 1;
        int[] result = searchRange(nums,target);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            int num = nums[mid];

            if (num == target) {
                int findLeft = mid;
                int findRight = mid;

                while (findLeft - 1 >= 0 && nums[findLeft - 1] == target) {
                    findLeft--;
                }

                while (findRight + 1 < nums.length && nums[findRight + 1] == target) {
                    findRight++;
                }

                return new int[]{findLeft, findRight};
            }

            if (num > target) {
                right = mid - 1;
            } else if (num < target) {
                left = mid + 1;
            }
        }

        return new int[]{-1, -1};
    }
}
