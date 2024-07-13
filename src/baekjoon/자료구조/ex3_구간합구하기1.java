package baekjoon.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ex3_구간합구하기1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int suNo = Integer.parseInt(stringTokenizer.nextToken());   // 배열의 숫자 개수
        int quizNo = Integer.parseInt(stringTokenizer.nextToken());     // 질의 개수

        // 구간 합 선언
        long[]S = new long[suNo+1];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for(int i=1; i <= suNo; i++){
            S[i] = S[i-1] + Integer.parseInt(stringTokenizer.nextToken());
        }

        for(int j =0; j < quizNo; j++){
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(stringTokenizer.nextToken());
            int end = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println(S[end] - S[start-1]);
        }
    }
}
