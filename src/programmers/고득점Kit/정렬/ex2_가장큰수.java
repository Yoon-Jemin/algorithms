package programmers.고득점Kit.정렬;
import java.util.*;

public class ex2_가장큰수 {
    public String solution(int[] numbers) {
        String answer = "";

        String[] strNumbers = new String[numbers.length];

        for(int i = 0; i < numbers.length; i++){
            strNumbers[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(strNumbers, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        for(String s : strNumbers) answer += s;

        return answer.charAt(0) == '0' ? "0" : answer;
    }
}
