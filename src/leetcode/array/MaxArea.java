package leetcode.array;

public class MaxArea {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int area = calculateArea(left, right, height);
        while (left < right) {
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
            area = Math.max(area, calculateArea(left, right, height));
        }

        return area;
    }

    private static int calculateArea(int left, int right, int[] height) {
        return (right - left) * (Math.min(height[left], height[right]));
    }
}
