package programmers.level_4;

public class 올바른괄호의개수 {

    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    public static int solution(int n) {
        int[] dp1 = new int[n + 1];
        int[] dp2 = new int[n + 1];

        dp1[0] = 1;
        dp2[0] = 1;

        for(int i = 1; i <= n; i++) {
            int sum = 0;
            dp2[i] = dp1[i - 1];
            for(int j = 0; j <= i; j++) {
                sum += dp1[i-j] * dp2[j];
            }
            dp1[i] = sum;
        }

        return dp1[n];
    }
}
