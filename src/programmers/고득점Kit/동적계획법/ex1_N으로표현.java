package programmers.고득점Kit.동적계획법;

import java.util.*;

public class ex1_N으로표현 {

    public static void main(String[] args) {
        int N = 5;
        int number = 12;
        System.out.println(solution(N,number));
    }

    public static int solution(int N, int number) {

        int answer = -1;

        Set<Integer>[] setArr = new Set[9];

        // 초기화
        int t = N;
        for(int i = 1; i < 9; i++){
            setArr[i] = new HashSet<>();
            setArr[i].add(t);
            t = t * 10 + N;
        }

        for(int i = 1; i < 9; i ++){
            for(int j = 1; j < i; j++){
                for(Integer a : setArr[j]){     // j개 만큼의 N을 쓴 숫자 모음
                    for(Integer b : setArr[i - j]){     // (i - j)개 만큼의 N을 쓴 숫자 모음
                        setArr[i].add(a + b);
                        setArr[i].add(a - b);
                        setArr[i].add(b - a);
                        setArr[i].add(a * b);
                        if(b != 0) setArr[i].add(a / b);
                        if(a != 0) setArr[i].add(b / a);
                    }
                }
            }
        }

        for(int i = 1; i < 9; i++){
            if(setArr[i].contains(number)){
                answer = i;
                break;
            }
        }

        return answer;

    }
}
