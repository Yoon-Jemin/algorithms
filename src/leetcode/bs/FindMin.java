package leetcode.bs;

public class FindMin {

    public static void main(String[] args) {
//        int[] nums1 = {4,5,6,7,0,1,2};
//        int[] nums2 = {3,4,5,6,7,8,0,1,2};
        int[] nums3 = {11,13,15,17};
        System.out.println(findMin(nums3));
    }

    public static int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (mid + 1 <= nums.length - 1 && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            if (mid + 1 <= nums.length - 1 && nums[mid + 1] > nums[right]) {  // mid 기준 오른쪽에 끊긴 부분이 있음
                left = mid + 1;
            } else {
                right = mid - 1;
            }

            min = Math.min(min, nums[mid]);
        }

        return min;
    }
}
