package leetcode.array;

public class Trap {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap1(height));
    }

    public static int trap1(int[] height) {
        int left = 0, right = height.length - 1;
        int maxLeft = 0, maxRight = 0;
        int answer = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    answer += maxLeft - height[left];
                }
                left++;
            } else {
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    answer += maxRight - height[right];
                }
                right--;
            }
        }

        return answer;
    }

    // MLE 발생
    public static int trap2(int[] height) {
        int maxHeight = 0;
        for (int i = 0; i < height.length; i++) {
            maxHeight = Math.max(maxHeight, height[i]);
        }

        int[][] map = new int[height.length][maxHeight];
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            for (int j = 0; j < h; j++) {
                map[i][j] = 1;
            }
        }

        int answer = 0;
        for (int j = 0; j < maxHeight; j++) {
            int start = 0;
            int water = 0;
            for (int i = 0; i < height.length; i++) {
                if (start == 0 && map[i][j] == 0) continue;

                if (map[i][j] == 1) {
                    start = 1;
                    answer += water;
                    water = 0;
                } else if (map[i][j] == 0) {
                    water++;
                }
            }
        }

        return answer;
    }
}
