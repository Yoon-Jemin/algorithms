package programmers.고득점Kit.정렬;
import java.util.*;
public class ex3_H인덱스 {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);
        int size = citations.length;

        if(citations[size-1] == 0) return 0;

        int numCite = size / 2;

        while(true){
            int lowerCite = 0;
            int higherCite = 0;
            for(int cite : citations){
                if(cite >= numCite) higherCite++;
                else lowerCite++;
            }
            if(numCite <= higherCite && numCite >= lowerCite){
                while(numCite <= higherCite && numCite >= lowerCite){
                    numCite++;
                    lowerCite = 0;
                    higherCite = 0;
                    for(int cite : citations){
                        if(cite >= numCite) higherCite++;
                        else lowerCite++;
                    }
                }
                numCite--;
                break;
            }
            else if(numCite > higherCite) numCite++;
            else if (numCite < higherCite) numCite--;
        }

        answer = numCite;
        return answer;
    }
}
