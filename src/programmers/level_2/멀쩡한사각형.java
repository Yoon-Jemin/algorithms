package programmers.level_2;

public class 멀쩡한사각형 {
    public long solution(int w, int h) {
        long answer = 0;
        double pre = -1 * ((double) h / (double) w);

        for (int x = 1; x < w; x++) {
            double num = pre * x + h;
            answer += (long) num;
        }

        return answer * 2;
    }
}
