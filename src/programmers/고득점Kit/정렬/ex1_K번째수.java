package programmers.고득점Kit.정렬;
import java.util.*;

public class ex1_K번째수 {
    public int[] solution(int[] array, int[][] commands) {
        int size = commands.length;;
        int[] answer = new int[size];

        for(int i = 0; i < size; i++){
            int[] condition = commands[i];
            int[] sliceArray = Arrays.copyOfRange(array, condition[0]-1, condition[1]);
            Arrays.sort(sliceArray);

            int number = sliceArray[condition[2]-1];

            answer[i] = number;
        }

        return answer;
    }
}
