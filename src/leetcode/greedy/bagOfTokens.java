package leetcode.greedy;

import java.util.Arrays;

public class bagOfTokens {

    public static void main(String[] args) {
        int[] tokens = {100, 200};
        int power = 150;

        System.out.println(bagOfTokensScore(tokens, power));
    }

    public static int bagOfTokensScore(int[] tokens, int power) {
        int score = 0;
        Arrays.sort(tokens);

        int startIndex = 0;
        int endIndex = tokens.length - 1;

        while (startIndex <= endIndex) {
            if (tokens[startIndex] <= power) {
                power -= tokens[startIndex];
                startIndex++;
                score++;
            } else {
                if (endIndex == startIndex) break;
                if (score > 0) {
                    power += tokens[endIndex];
                    endIndex--;
                    score--;
                } else {
                    break;
                }
            }
        }

        return score;
    }
}
