package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 톱니바퀴 {

    static Deque<Integer>[] wheels;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        wheels = new Deque[5];
        for (int i = 1; i < wheels.length; i++) {
            wheels[i] = new LinkedList<>();
        }

        for (int i = 1; i <= 4; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < 8; j++) {
                wheels[i].offer(Integer.parseInt(s.substring(j, j + 1)));
            }
        }

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 회전 횟수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int wheel = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            simulate(wheel, direction);
        }

        int answer = 0;
        for (int i = 1; i <= 4; i++) {
            if (wheels[i].peekFirst() == 1) {
                if (i == 1) answer += 1;
                if (i == 2) answer += 2;
                if (i == 3) answer += 4;
                if (i == 4) answer += 8;
            }
        }

        System.out.println(answer);
    }

    private static void simulate(int wheelNum, int direction) {
        int[] rotateDir = new int[5];
        rotateDir[wheelNum] = direction;

        for (int i = wheelNum; i > 1; i--) {
            if (getRight(wheels[i - 1]) == getLeft(wheels[i])) break;
            rotateDir[i - 1] = -1 * rotateDir[i];
        }

        for (int i = wheelNum; i < 4; i++) {
            if (getRight(wheels[i]) == getLeft(wheels[i + 1])) break;
            rotateDir[i + 1] = -1 * rotateDir[i];
        }

        for (int i = 0; i < 5; i++) {
            if (rotateDir[i] == -1) turnAntiClockWise(wheels[i]);
            if (rotateDir[i] == 1) turnClockWise(wheels[i]);
        }
    }

    private static int getRight(Deque<Integer> wheel) {
        return (int) wheel.toArray()[2];
    }

    private static int getLeft(Deque<Integer> wheel) {
        return (int) wheel.toArray()[6];
    }

    private static void turnClockWise(Deque<Integer> wheel) {
        wheel.addFirst(wheel.pollLast());   // 시계 방향
    }

    private static void turnAntiClockWise(Deque<Integer> wheel) {
        wheel.addLast(wheel.pollFirst());  // 반시계 방향
    }
}
