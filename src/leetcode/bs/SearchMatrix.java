package leetcode.bs;

public class SearchMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        int target = 13;
        System.out.println(searchMatrix(matrix,target));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int min_X = 0;
        int max_X = matrix.length - 1;

        int min_Y = 0;
        int max_Y = matrix[0].length - 1;

        while (min_X <= max_X) {
            int mid_X = min_X + (max_X - min_X) / 2;

            if (target > matrix[mid_X][max_Y]) {
                min_X = mid_X + 1;
                continue;
            }
            else if (target < matrix[mid_X][0]) {
                max_X = mid_X - 1;
                continue;
            }
            for (int y = min_Y; y <= max_Y; y++) {
                if (matrix[mid_X][y] == target) return true;
            }
            return false;
        }

        return false;
    }
}
