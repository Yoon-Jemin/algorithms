package programmers.level_3;

public class 연속펄스부분수열의합 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{6, -7, 16, 3, -4}));
    }

    public static long solution(int[] sequence) {
        long answer = 0;
        int size = sequence.length;

        int[] pulse1 = new int[size];
        int[] pulse2 = new int[size];

        for (int i = 0; i < size; i++) {
            pulse1[i] = (i % 2 == 0) ? 1 : -1;
            pulse2[i] = (i % 2 == 0) ? -1 : 1;
        }

        long[] dp1 = new long[size];
        long[] dp2= new long[size];
        dp1[0] = sequence[0];
        dp2[0] = -sequence[0];

        answer = Math.max(dp1[0], dp2[0]);

        for (int i = 1; i < size; i++) {
            dp1[i] = Math.max(dp1[i - 1] + sequence[i] * pulse1[i], sequence[i] * pulse1[i]);
            dp2[i] = Math.max(dp2[i - 1] + sequence[i] * pulse2[i], sequence[i] * pulse2[i]);
            answer = Math.max(answer, dp1[i]);
            answer = Math.max(answer, dp2[i]);
        }

        return answer;
    }


}
