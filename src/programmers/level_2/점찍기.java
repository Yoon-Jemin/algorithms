package programmers.level_2;

public class 점찍기 {

    public static void main(String[] args) {
        int k = 2;
        int d = 4;
        System.out.println(solution(k, d));
    }

    public static long solution(int k, int d) {
        long answer = 0;

        for (long x = 0; x <=d; x += k) {
            int max_Y = (int) Math.sqrt((long)d*d - x*x);
            answer += max_Y / k + 1;
        }

        return answer;
    }

}
