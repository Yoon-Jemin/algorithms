package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 미친아두이노 {

    static int N, M;
    static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int[] jongsu = new int[2];
        List<int[]> madMachines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == 'I') {
                    jongsu[0] = i;
                    jongsu[1] = j;
                }
                if (map[i][j] == 'R') {
                    madMachines.add(new int[] {i, j});
                }
            }
        }

        String input = br.readLine();
        for (int turn = 1; turn <= input.length(); turn++) {
            char chrDir = input.charAt(turn - 1);
            int dir = Integer.parseInt(String.valueOf(chrDir));

            // 종수 움직이기
            int nx = jongsu[0] + dx[dir];
            int ny = jongsu[1] + dy[dir];

            if (map[nx][ny] == 'R') {
                System.out.println("kraj " + turn);
                return;
            }

            map[jongsu[0]][jongsu[1]] = '.';
            map[nx][ny] = 'I';
            jongsu[0] = nx;
            jongsu[1] = ny;

            // 아두이노 움직이기
            Map<String, Integer> nextMachine = new HashMap<>();
            for (int[] machine : madMachines) {
                int min = Integer.MAX_VALUE;
                int[] location = new int[2];
                for (int i = 1; i <= 9; i++) {
                    int machineX = machine[0] + dx[i];
                    int machineY = machine[1] + dy[i];

                    if (min > Math.abs(machineX - jongsu[0]) + Math.abs(machineY - jongsu[1])) {
                        min = Math.abs(machineX - jongsu[0]) + Math.abs(machineY - jongsu[1]);
                        location[0] = machineX;
                        location[1] = machineY;
                    }
                }

                if (location[0] == jongsu[0] && location[1] == jongsu[1]) {
                    System.out.println("kraj " + turn);
                    return;
                }

                String key = location[0] + "," + location[1];
                nextMachine.put(key, nextMachine.getOrDefault(key, 0) + 1);
            }

            for (int[] machine : madMachines) {
                map[machine[0]][machine[1]] = '.';
            }
            madMachines.clear();

            for (String key : nextMachine.keySet()) {
                if (nextMachine.get(key) >= 2) continue;

                String[] s = key.split(",");
                madMachines.add(new int[] {Integer.parseInt(s[0]), Integer.parseInt(s[1])});
                map[Integer.parseInt(s[0])][Integer.parseInt(s[1])] = 'R';
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
