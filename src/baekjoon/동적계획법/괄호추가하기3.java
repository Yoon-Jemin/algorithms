package baekjoon.동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 괄호추가하기3 {  // todo: 다시 풀기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String expr = st.nextToken();

        int numCount = (N + 1) / 2;
        int[] nums = new int[numCount];
        char[] ops = new char[numCount - 1];
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) nums[i / 2] = expr.charAt(i) - '0';
            else ops[i / 2] = expr.charAt(i);
        }

        int[][] dpMax = new int[numCount][numCount];
        int[][] dpMin = new int[numCount][numCount];

        // 초기값: 구간의 길이가 1일 때 (숫자 하나만 있을 때)
        for (int i = 0; i < numCount; i++) {
            dpMax[i][i] = nums[i];
            dpMin[i][i] = nums[i];
        }

        for (int len = 2; len <= numCount; len++) {
            for (int i = 0; i + len - 1 < numCount; i++) {
                int j = i + len - 1;
                dpMax[i][j] = Integer.MIN_VALUE;
                dpMin[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    char op = ops[k];

                    int[] candidates = new int[4];
                    candidates[0] = calc(dpMax[i][k], dpMax[k + 1][j], op);
                    candidates[1] = calc(dpMax[i][k], dpMin[k + 1][j], op);
                    candidates[2] = calc(dpMin[i][k], dpMax[k + 1][j], op);
                    candidates[3] = calc(dpMin[i][k], dpMin[k + 1][j], op);

                    for (int val : candidates) {
                        dpMax[i][j] = Math.max(dpMax[i][j], val);
                        dpMin[i][j] = Math.min(dpMin[i][j], val);
                    }
                }
            }
        }

        System.out.println(dpMax[0][numCount - 1]);
    }

    private static int calc(int a, int b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b;
    }
}
