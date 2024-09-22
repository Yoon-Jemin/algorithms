package programmers.level_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class 시소짝꿍 {

    public static void main(String[] args) {
        int[] weights = {100,180,360,100,270};
        System.out.println(solution(weights));
    }

//    {100, 100} 은 같은 거리
//    {180, 360} 은 각각 4(m), 2(m) 거리
//    {180, 270} 은 각각 3(m), 2(m) 거리
//    {270, 360} 은 각각 4(m), 3(m) 거리

    public static long solution(int[] weights) {
        long answer = 0;

        long[] cntWeight = new long[1001];
        long[] mulWeight = new long[4001];

        for(int i =0; i < weights.length; i++) {
            long temp = cntWeight[weights[i]];

            if(temp > 0){
                answer += temp;
                answer += mulWeight[weights[i]*2] - temp;
                answer += mulWeight[weights[i]*3] - temp;
                answer += mulWeight[weights[i]*4] - temp;
            } else {
                answer += mulWeight[weights[i]*2];
                answer += mulWeight[weights[i]*3];
                answer += mulWeight[weights[i]*4];
            }

            cntWeight[weights[i]]++;
            mulWeight[weights[i]*2]++;
            mulWeight[weights[i]*3]++;
            mulWeight[weights[i]*4]++;
        }

        return answer;
    }
}
