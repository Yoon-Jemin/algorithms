package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 괄호추가하기 {

    static int N;
    static int answer = Integer.MIN_VALUE;
    static int[] nums;
    static String[] ops;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String equation = st.nextToken();

        nums = new int[N / 2 + 1];
        ops = new String[N / 2];
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) nums[i / 2] = Integer.parseInt(equation.substring(i, i + 1));
            else ops[i / 2] = equation.substring(i, i + 1);
        }

        dfs(0, nums[0]);  // 지금까지 고려한 연산자의 인덱스, 지금까지 계산한 값
        System.out.println(answer);
    }

    private static void dfs(int idx, int current) {
        if (idx == ops.length) {
            answer = Math.max(answer, current);
            return;
        }

        // 괄호를 안 치는 경우
        int next = calc(current, ops[idx], nums[idx + 1]);
        dfs(idx + 1, next);

        // 괄호를 치는 경우
        if (idx + 1 < ops.length) {
            int bracket = calc(nums[idx + 1], ops[idx + 1], nums[idx + 2]);
            int next2 = calc(current, ops[idx], bracket);
            dfs(idx + 2, next2);
        }
    }

    private static int calc(int a, String op, int b) {
        if (op.equals("+")) return a + b;
        if (op.equals("-")) return a - b;
        return a * b;
    }
}
