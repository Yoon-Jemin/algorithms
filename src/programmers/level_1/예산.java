package programmers.level_1;

import java.util.Arrays;

public class 예산 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,3,2,5,4}, 9));
    }

    public static int solution(int[] d, int budget) {
        int answer = 0;

        Arrays.sort(d);

        int sum = 0;
        for(int i = 0; i < d.length; i++) {
            sum += d[i];
            if(budget < sum) break;
            answer++;
        }

        return answer;
    }
}
