package programmers.스택큐;
import java.util.*;

public class ex2_기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer;

        ArrayList<Integer> answerList = new ArrayList<>();

        int flag = 0;
        int sum = 0;

        while(sum != progresses.length){
            while(progresses[flag] < 100){
                for(int i = flag; i < progresses.length; i++){
                    progresses[i] += speeds[i];
                }
            }
            int num = 0;
            for(int i = flag; i < progresses.length; i++){
                if(progresses[i] >= 100) num++;
                else{
                    flag = i;
                    break;
                }
            }
            answerList.add(num);

            sum = 0;
            for(int n : answerList){
                sum += n;
            }
        }

        answer = new int[answerList.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
