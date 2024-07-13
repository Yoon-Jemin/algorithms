package programmers;

import java.util.*;
import java.util.stream.Collectors;

public class practice {
    public static void main(String[] args) {

        int[] citations = {0,0,0,1};

        System.out.println(solution(citations));

    }
    public static int solution(int[] citations) {
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
