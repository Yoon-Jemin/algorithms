package leetcode.bs;

public class Search2 {

    public static void main(String[] args) {
        int[] nums = {1,0,1,1,1};
        int target = 0;

        System.out.println(search(nums, target));
    }

    public static boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) return true;
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
            } else if (nums[mid] <= nums[right]) { //오른쪽 쭉 증가
                if (nums[mid] <= target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            } else if (nums[left] <= nums[mid]) {   // 왼쪽은 쭉 증가
                if (nums[left] <= target && target <= nums[mid]) right = mid - 1;
                else left = mid + 1;
            }
        }

        return false;
    }
}
