package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 컨베이어벨트 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 컨테이버 벨트의 최대 길이 : 2N
        int K = Integer.parseInt(st.nextToken());   // 내구도가 0인 칸의 최대 개수

        int[] belt = new int[2 * N];
        boolean[] robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) belt[i] = Integer.parseInt(st.nextToken());

        int turn = 0;
        while (true) {
            turn++;

            // 1. 벨트 회전
            int last = belt[2 * N - 1];
            for (int i = 2 * N - 1; i > 0; i--) belt[i] = belt[i - 1];
            belt[0] = last;

            for (int i = N - 1; i > 0; i--) robot[i] = robot[i - 1];
            robot[0] = false;
            robot[N - 1] = false;

            // 2. 로봇 이동
            for (int i = N - 1; i > 0; i--) {
                if (robot[i - 1] && !robot[i] && belt[i] > 0) {
                    robot[i] = true;
                    robot[N - 1] = false;
                    belt[i]--;
                }
            }
            robot[N - 1] = false;

            // 3. 새 로봇 올리기
            if (belt[0] > 0) {
                robot[0] = true;
                belt[0]--;
            }

            // 4. 내구도 0 개수 체크
            int zero = 0;
            for (int b : belt) if (b == 0) zero++;
            if (zero >= K) break;
        }

        System.out.println(turn);
    }
}
