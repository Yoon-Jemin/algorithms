package programmers.고득점Kit.완전탐색;

public class ex1_최소직사각형 {
    public static void main(String[] args) {

        int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
        System.out.println(solution(sizes));

    }

    public static int solution(int[][] sizes) {
        int maxWidth = 0;
        int maxHeight = 0;

        for(int[] size : sizes){
            if(size[0] < size[1]){
                int temp = size[0];
                size[0] = size[1];
                size[1] = temp;
            }
            maxWidth = Math.max(maxWidth, size[0]);
            maxHeight = Math.max(maxHeight, size[1]);
        }

        return maxWidth * maxHeight;
    }
}
