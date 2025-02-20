package programmers.고득점Kit.탐욕법;

import java.util.*;

public class ex1_체육복 {

    public static void main(String[] args) {

        int[] lost = {2, 4};
        int[] reserve = {3};

        System.out.println(solution(5, lost, reserve));

    }

    public static int solution(int n, int[] losts, int[] reserves) {

        int noClass = 0;

        Map<Integer ,Integer> studentMap = new HashMap<>();     // [학생, 옷 개수]

        for(int i = 1; i <= n; i++){
            studentMap.put(i,1);
        }

        for(int lost : losts){
           studentMap.put(lost, 0);
        }

        for(int reserve : reserves){
            studentMap.put(reserve, studentMap.get(reserve) + 1);
        }

        for(int i = 1; i <= n; i++){
            if(studentMap.get(i) == 0){
                if(i > 1 && studentMap.get(i-1) == 2){
                    studentMap.put(i, 1);
                    studentMap.put(i-1, 1);
                } else if (i < n && studentMap.get(i + 1) == 2) {
                    studentMap.put(i, 1);
                    studentMap.put(i+1, 1);
                } else {
                    noClass++;
                }
            }
        }

        return n - noClass;
    }
}
