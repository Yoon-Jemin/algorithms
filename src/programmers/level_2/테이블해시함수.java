package programmers.level_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 테이블해시함수 {

    public static void main(String[] args) {
        int[][] data = {
                {2,2,6},
                {1,5,10},
                {4,2,9},
                {3,8,3}
        };

        int col = 2;
        int row_begin = 2;
        int row_end = 3;

        System.out.println(solution(data, col, row_begin, row_end));
    }

    public static int solution(int[][] data, int col, int row_begin, int row_end) {
        List<int[]> list = new ArrayList<>();
        for (int[] d : data) list.add(d);

        Collections.sort(list, (a, b) -> {
            if (a[col - 1] == b[col - 1]) return b[0] - a[0];
            else return a[col - 1] - b[col - 1];
        });

        int[] S = new int[data.length];
        for (int i = 0; i < S.length; i++) {
            int mod = i + 1;

            for (int j = 0; j < list.get(i).length; j++) {
                S[i] += list.get(i)[j] % mod;
            }
        }

        int answer = S[row_begin - 1];
        for (int i = row_begin; i <= row_end - 1; i++) {
            answer = answer ^ S[i];
        }

        return answer;
    }
}
