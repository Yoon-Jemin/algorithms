package programmers.level_3;

import java.util.*;

public class 인사고과 {

    public static void main(String[] args) {
        int[][] scores = {{2,2},{1,4},{3,2},{3,2},{2,1}};
        System.out.println(solution(scores));
    }

    public static int solution(int[][] scores) {
        int[] wanho = scores[0];

        // 근무 태도 내림차순, 동료 평가 오름 차순
        Arrays.sort(scores, (a,b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        int maxPeerScore = 0;
        List<int[]> validScores = new ArrayList<>();
        for (int[] score : scores) {
            if (score[1] < maxPeerScore) {
                if (Arrays.equals(wanho, score)) return -1;
                continue;
            }
            validScores.add(score);
            maxPeerScore = Math.max(maxPeerScore, score[1]);
        }

        validScores.sort((a, b) -> (b[0] + b[1]) - (a[0] + a[1]));

        int rank = 1;
        int count = 1;
        int recentScore = validScores.get(0)[0] + validScores.get(0)[1];
        for (int[] score : validScores) {
            int totalScore = score[0] + score[1];
            if (totalScore < recentScore) {
                rank = count;
            }
            count++;
            if (Arrays.equals(wanho, score)) return rank;
            recentScore = totalScore;
        }

        return -1;
    }
}