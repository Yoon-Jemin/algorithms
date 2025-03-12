package programmers.level_2;

import java.util.PriorityQueue;

public class 땅따먹기 {

    public static void main(String[] args) {
        int[][] land = {{1,2,3,5},{5,6,7,8},{4,3,2,1}};
        System.out.println(solution(land));
    }

    public static int solution(int[][] land) {
        int[][] dp = new int[land.length][4];

        dp[0][0] = land[0][0];
        dp[0][1] = land[0][1];
        dp[0][2] = land[0][2];
        dp[0][3] = land[0][3];

        for (int i = 1; i < land.length; i++) {
            dp[i][0] = land[i][0] + Math.max(Math.max(dp[i-1][1], dp[i-1][2]), dp[i-1][3]);
            dp[i][1] = land[i][1] + Math.max(Math.max(dp[i-1][0], dp[i-1][2]), dp[i-1][3]);
            dp[i][2] = land[i][2] + Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][3]);
            dp[i][3] = land[i][3] + Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
        }

        return Math.max(Math.max(dp[land.length-1][0], dp[land.length-1][1]), Math.max(dp[land.length-1][2], dp[land.length-1][3]));
    }
}
