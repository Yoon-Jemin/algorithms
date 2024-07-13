package programmers.완전탐색;

import java.util.ArrayList;
import java.util.List;

public class ex2_모의고사 {

    public static void main(String[] args) {

        int[] answers = {1,3,2,4,2};

        int[] output = solution(answers);
        for(int i : output){
            System.out.println(i);
        }
    }

    public static int[] solution(int[] answers) {
        int[] numCorrect = new int[3];
        int size = answers.length;

        int[] guess1 = new int[size];
        int[] pattern1 = {1, 2, 3, 4, 5};
        for(int i = 0; i <size; i++){
            guess1[i] = pattern1[i % pattern1.length];
        }
        int answer1 = 0;

        int[] guess2 = new int[size];
        int[] pattern2 = {2, 1, 2, 3, 2, 4, 2, 5};
        for(int i = 0; i <size; i++){
            guess2[i] = pattern2[i % pattern2.length];
        }
        int answer2 = 0;

        int[] guess3 = new int[size];
        int[] pattern3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        for(int i = 0; i <size; i++){
            guess3[i] = pattern3[i % pattern3.length];
        }
        int answer3 = 0;

        for(int i = 0; i < size; i++){
            if(answers[i] == guess1[i]) numCorrect[0]++;
            if(answers[i] == guess2[i]) numCorrect[1]++;
            if(answers[i] == guess3[i]) numCorrect[2]++;
        }

        int max = 0;
        int maxIndex = -1;
        for(int i = 0; i < 3; i++){
            if(max < numCorrect[i]){
                max = numCorrect[i];
                maxIndex = i;
            }
        }

        ArrayList<Integer> maxList = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            if(max == numCorrect[i]){
                maxList.add(i+1);
            }
        }

        int[] answerList = new int[maxList.size()];
        for(int i = 0; i < answerList.length; i++){
            answerList[i] = maxList.get(i);
        }

        return answerList;

    }
}
