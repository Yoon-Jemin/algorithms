package programmers.level_2;

import java.util.ArrayList;

public class 쿼드압축후개수세기 {

    public static void main(String[] args) {
//        int[][] arr = new int[][]{
//                {1,1,0,0},
//                {1,0,0,0},
//                {1,0,0,1},
//                {1,1,1,1}
//        };
        int[][] arr = new int[][]{
                {1,1,1,1,1,1,1,1},
                {0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1},
                {0,1,0,0,1,1,1,1},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,1,0,0,1},
                {0,0,0,0,1,1,1,1}
        };
        int[] result = solution(arr);
        for(int i : result) {
            System.out.println(i);
        }
    }

    static int zero = 0;
    static int one = 0;
    static boolean[][] isCompressed;
    public static int[] solution(int[][] arr) {

        isCompressed = new boolean[arr.length][arr[0].length];

        int length = arr.length;    // 8
        while(length > 0) {
            check(arr, length);
            length /= 2;    // 4
        }

        return new int[]{zero, one};
    }

    private static void check(int[][] arr, int length) {    // length = 2
        boolean[][] isVisited = new boolean[arr.length][arr[0].length];

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                if(!isVisited[i][j] && !isCompressed[i][j]) {
                    ArrayList<int[]> list = new ArrayList<>();
                    boolean isSame = true;
                    int num = arr[i][j];
                    for(int k = 0; k < length; k++) {
                        for(int l = 0; l < length; l++) {
                            list.add(new int[]{i+k, j+l});
                            isVisited[i+k][j+l] = true;
                            if(arr[i+k][j+l] != num) isSame = false;

                        }
                    }
                    if(isSame) {
                        for(int[] location : list) {
                            isCompressed[location[0]][location[1]] = true;
                        }
                        if(num == 1) one++;
                        if(num == 0) zero++;
                    }
                }
            }
        }
    }
}
