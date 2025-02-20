package programmers.고득점Kit.완전탐색;

public class ex4_카펫 {
    public static void main(String[] args) {

//        System.out.println(48 % 11);

        int[] answer = solution(10,2);
        for(int num : answer) System.out.println(num);

    }
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int area = brown + yellow;

        for(int width = 1; width <= area; width++){
            if(area % width == 0){
                int height = area / width;
                if(2*(width + height) == (brown + 4)){
                    answer[1] = width;
                    answer[0] = height;
                    return  answer;
                }
            }
        }

        return answer;
    }
}
