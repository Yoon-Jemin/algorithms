package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위굴리기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());   // 명령의 개수

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dice = new int[6];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int direction = Integer.parseInt(st.nextToken());

            if (direction == 1) {
                if (y == m - 1) continue;
                dice = rollEast(dice);
                y++;
            } else if (direction == 2) {
                if (y == 0) continue;
                dice = rollWest(dice);
                y--;
            } else if (direction == 3) {
                if (x == 0) continue;
                dice = rollNorth(dice);
                x--;
            } else if (direction == 4) {
                if (x == n - 1) continue;
                dice = rollSouth(dice);
                x++;
            }

            if (map[x][y] == 0) {
                map[x][y] = dice[1];
            } else {
                dice[1] = map[x][y];
                map[x][y] = 0;
            }

            System.out.println(dice[3]);
        }
    }

    public static int[] rollEast(int[] dice) {
        int[] newDice = new int[6];

        newDice[0] = dice[0];
        newDice[1] = dice[4];
        newDice[2] = dice[2];
        newDice[3] = dice[5];
        newDice[4] = dice[3];
        newDice[5] = dice[1];

        return newDice;
    }

    public static int[] rollWest(int[] dice) {
        int[] newDice = new int[6];

        newDice[0] = dice[0];
        newDice[1] = dice[5];
        newDice[2] = dice[2];
        newDice[3] = dice[4];
        newDice[4] = dice[1];
        newDice[5] = dice[3];

        return newDice;
    }

    public static int[] rollNorth(int[] dice) {
        int[] newDice = new int[6];

        newDice[0] = dice[1];
        newDice[1] = dice[2];
        newDice[2] = dice[3];
        newDice[3] = dice[0];
        newDice[4] = dice[4];
        newDice[5] = dice[5];

        return newDice;
    }

    public static int[] rollSouth(int[] dice) {
        int[] newDice = new int[6];

        newDice[0] = dice[3];
        newDice[1] = dice[0];
        newDice[2] = dice[1];
        newDice[3] = dice[2];
        newDice[4] = dice[4];
        newDice[5] = dice[5];

        return newDice;
    }
}
