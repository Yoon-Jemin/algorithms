package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 원판돌리기 {

    static int N;
    static int M;
    static int T;
    static List<List<Integer>> wheels;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 원판의 개수
        M = Integer.parseInt(st.nextToken());   // 원판에 적힌 정수의 개수
        T = Integer.parseInt(st.nextToken());   // 회전 수

        wheels = new ArrayList<>();
        wheels.add(new ArrayList<>());  // 0 번째 바귀

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            List<Integer> wheel = new ArrayList<>();

            for (int j = 0; j < M; j++) wheel.add(Integer.parseInt(st.nextToken()));
            wheels.add(wheel);
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());   // 번호가 x의 배수인 원판
            int d = Integer.parseInt(st.nextToken());   // 방향 (시계: 0, 반시계:1)
            int k = Integer.parseInt(st.nextToken());   // 회전 수

            // 회전 시키기
            int index = 1;
            int wheel = x * index;

            while (wheel <= N) {
                spin(wheel, d, k);
                index++;
                wheel = x * index;
            }

            boolean erased = false;
            List<int[]> numsToRemove = new ArrayList<>();

            // 인접한 수 지우기
            for (int wheelNum = 1; wheelNum <= N; wheelNum++) {
                for (int wheelIndex = 0; wheelIndex < M; wheelIndex++) {
                    int numOnWheel = wheels.get(wheelNum).get(wheelIndex);
                    if (numOnWheel == -1) continue;

                    // 양 옆
                    int nextRightWheel = wheelIndex + 1 >= M ? 0 : wheelIndex + 1;
                    if (wheels.get(wheelNum).get(nextRightWheel) == numOnWheel) {
                        numsToRemove.add(new int[] {wheelNum, nextRightWheel});
                    }

                    int nextLeftWheel = wheelIndex - 1 < 0 ? M - 1 : wheelIndex - 1;
                    if (wheels.get(wheelNum).get(nextLeftWheel) == numOnWheel) {
                        numsToRemove.add(new int[] {wheelNum, nextLeftWheel});
                    }

                    // 위 바퀴
                    if (wheelNum + 1 <= N) {
                        if (wheels.get(wheelNum + 1).get(wheelIndex) == numOnWheel) {
                            numsToRemove.add(new int[] {wheelNum + 1, wheelIndex});
                        }
                    }

                    // 아래 바퀴
                    if (wheelNum - 1 >= 1) {
                        if (wheels.get(wheelNum - 1).get(wheelIndex) == numOnWheel) {
                            numsToRemove.add(new int[] {wheelNum - 1, wheelIndex});
                        }
                    }
                }
            }

            if (numsToRemove.size() >= 2) {     // 지우기
                for (int[] remove : numsToRemove) {
                    wheels.get(remove[0]).set(remove[1], -1);
                    erased = true;
                }
            }

            if (!erased) {
                float avg = getAverage();
                if (avg != -1) updateWheels(avg);
            }
        }

        int sum = 0;
        for (int i = 1; i <= N; i++) {
            List<Integer> wheel = wheels.get(i);

            for (int num : wheel) {
                if (num != -1) sum += num;
            }
        }

        System.out.println(sum);
    }

    private static void spin (int wheelNum, int direction, int count) {
        List<Integer> wheel = wheels.get(wheelNum);

        if (direction == 0) spinClockWise(wheel, count);
        else spinAntiClockWise(wheel, count);
    }

    private static void spinClockWise (List<Integer> wheel, int count) {
        for (int i = 0; i < count; i++) {
            int num = wheel.get(wheel.size() - 1);
            wheel.remove(wheel.size() - 1);
            wheel.add(0, num);
        }
    }

    private static void spinAntiClockWise(List<Integer> wheel, int count) {
        for (int i = 0; i < count; i++) {
            int num = wheel.get(0);
            wheel.remove(0);
            wheel.add(M - 1, num);
        }
    }

    private static float getAverage() {
        int sum = 0;
        int num = 0;

        for (int i = 1; i <= N; i++) {
            List<Integer> wheel = wheels.get(i);

            for (int w : wheel) {
                if (w != -1) {
                    sum += w;
                    num++;
                }
            }
        }

        if (num == 0) return -1;
        return (float) sum / num;
    }

    private static void updateWheels(float avg) {
        for (int i = 1; i <= N; i++) {
            List<Integer> wheel = wheels.get(i);
            for (int j = 0; j < wheel.size(); j++) {
                if (wheel.get(j) == -1) continue;

                if (wheel.get(j) < avg) wheel.set(j, wheel.get(j) + 1);
                else if (wheel.get(j) > avg) wheel.set(j, wheel.get(j) - 1);
            }
        }
    }
}
