package programmers.level_3;

public class 거스름돈 {

    public static void main(String[] args) {
        int n = 5;
        int[] money = {1,2,5};
        System.out.println(solution(n, money));
    }

    public static int solution(int n, int[] money) {

        int size = n + 1;
        int[] dp = new int[size];

        for (int m : money) {
            dp[m]++;
            for(int i = m; i < size; i++) {
                dp[i] += dp[i - m];
            }
        }

        return dp[n];
    }
}
