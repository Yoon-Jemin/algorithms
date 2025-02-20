package programmers.고득점Kit.동적계획법;

public class ex2_정수삼각형 {

    public static void main(String[] args) {

        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
//        int[][] triangle = {  {5}, {9, 2}, {3, 8, 1}, {2, 1, 7, 3}, {4, 5, 2, 6, 9}};
        System.out.println(solution(triangle));

    }

    public static int solution(int[][] triangle) {
        int height = triangle.length;

        for(int i = height - 2; i >= 0; i--){
            for(int j = 0; j < triangle[i].length ; j++){
                triangle[i][j] = Math.max(triangle[i+1][j], triangle[i+1][j+1]) + triangle[i][j];
            }
        }

        return triangle[0][0];
    }
}
