package programmers.level_2;

public class 숫자블록 {

    public static void main(String[] args) {
        long begin = 1;
        long end = 10;
        int[] answer = solution(begin, end);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    public static int[] solution(long begin, long end) {
        int[] answer = new int[(int) (end - begin + 1)];

        long num = begin;

        for (int i = 0; i < answer.length; i++) {
            int block = getBlock((int) num);
            answer[i] = block;
            num++;
        }

        if (begin == 1) answer[0] = 0;
        return answer;
    }

    public static int getBlock(int input) {
        int max = 1;

        for (int i = 2; i * i <= input; i++) {
            if (input % i == 0) {
                max = i;
                if(input / i <= 10000000) return input / i;
            }
        }

        return max;
    }
}
