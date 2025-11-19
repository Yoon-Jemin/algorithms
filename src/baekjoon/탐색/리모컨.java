package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 리모컨 {

    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int M = Integer.parseInt(br.readLine());
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        // 1. +-만 눌러 이동할 경우
        int answer = Math.abs(N - 100);

        // 2. 숫자 버튼으로 찍을 수 있는 모든 경우 탐색
        for (int i = 0; i <= 999999; i++) {
            int len = check(i);
            if (len > 0) {
                int press = Math.abs(i - N);
                answer = Math.min(answer, len + press);
            }
        }

        System.out.println(answer);
    }

    static int check(int num) {
        if (num == 0) {
            return broken[0] ? -1 : 1;
        }

        int len = 0;
        while (num > 0) {
            if (broken[num % 10]) return -1;
            num /= 10;
            len++;
        }
        return len;
    }
}
