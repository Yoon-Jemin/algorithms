package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 뱀 {

    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());   // 사과의 개수
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;  // 사과의 위치
        }

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());   // 방향 전환 정보

        Queue<int[]> queue = new LinkedList<>();    // 초 : 방향 (0: 왼쪽, 1: 오른쪽)
        for (int i = 1; i <= l; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();

            if (direction.equals("L")) {
                queue.add(new int[]{a, 0});
            } else {
                queue.add(new int[]{a, 1});
            }
        }

        int nowTime = 0;
        map[1][1] = 2;
        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[] {1, 1});

        String direction = "R";
        Map<String, int[]> directionMap = new HashMap<>();
        directionMap.put("R", new int[] {0, 1});
        directionMap.put("L", new int[] {0, -1});
        directionMap.put("U", new int[] {-1, 0});
        directionMap.put("D", new int[] {1, 0});
        while (true) {
            if (!queue.isEmpty()) {
                int[] peek = queue.peek();
                int changeTime = peek[0];
                int changeDirection = peek[1];
                if (changeTime == nowTime) {   // 방향 바꿀 시간이 됨
                    direction = findNextDirection(direction, changeDirection);
                    queue.poll();
                }
            }

            int[] head = deque.peekFirst();
            int[] move = directionMap.get(direction);
            int nextX = head[0] + move[0];
            int nextY = head[1] + move[1];

            if (nextX < 1 || nextX > N || nextY < 1 || nextY > N) break;    // 벽에 부딪힘
            if (map[nextX][nextY] == 2) break;  // 뱀 몸에 부딪힘

            deque.addFirst(new int[]{nextX, nextY});    // 새로운 머리
            if (map[nextX][nextY] == 1) {   // 사과를 먹음
                map[nextX][nextY] = 2;
            } else {    // 사과를 먹지 않음
                map[nextX][nextY] = 2;
                int[] tail = deque.pollLast();
                map[tail[0]][tail[1]] = 0;
            }

            nowTime++;
        }

        System.out.println(nowTime + 1);
    }


    private static String findNextDirection(String direction, int changeDirection) {
        // changeDirection: 0 (방향 기준 왼쪽)
        // changeDirection: 1 (방향 기준 오른쪽)
        if (direction.equals("R")) {
            if (changeDirection == 1) return "D";
            else return "U";
        } else if (direction.equals("L")) {
            if (changeDirection == 1) return "U";
            else return "D";
        } else if (direction.equals("U")) {
            if (changeDirection == 1) return "R";
            else return "L";
        } else if (direction.equals("D")) {
            if (changeDirection == 1) return "L";
            else return "R";
        }

        return null;
    }
}
