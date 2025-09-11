package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 야구 {

    static int N;
    static int[][] game;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 이닝 수
        game = new int[N][10];

        for (int i = 0; i < N; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                game[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        int[] turns = new int[10];
        turns[4] = 1;

        permutate(turns, 2,  0);
        System.out.println(answer);
    }

    private static void permutate(int[] turns, int player, int depth) {
        if (depth == 8) {
            startGame(turns);
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (turns[i] != 0) continue;    // 이미 선수가 있음
            turns[i] = player;
            permutate(turns, player + 1, depth + 1);
            turns[i] = 0;
        }
    }

    private static void startGame(int[] turns) {
        int totalScore = 0;
        int playerNum = 1;

        for (int i = 0; i < N; i++) {
            int outs = 0;
            boolean[] base = new boolean[3];
            Arrays.fill(base, false);

            while (outs < 3) {
                int player = turns[playerNum];
                int result = game[i][player];

                if (result == 1) {  // 안타
                    if (base[2]) {
                        totalScore++;
                        base[2] = false;
                    }
                    if (base[1]) {
                        base[2] = true;
                        base[1] = false;
                    }
                    if (base[0]) {
                        base[1] = true;
                    }
                    base[0] = true;
                } else if (result == 2) {   // 2루타
                    if (base[2]) {
                        totalScore++;
                        base[2] = false;
                    }
                    if (base[1]) {
                        totalScore++;
                        base[1] = false;
                    }
                    if (base[0]) {
                        base[2] = true;
                        base[0] = false;
                    }
                    base[1] = true;
                } else if (result == 3) {   // 3루타
                    for (int b = 0; b < 3; b++) {
                        if (base[b]) {
                            totalScore++;
                            base[b] = false;
                        }
                    }
                    base[2] = true;
                } else if (result == 4) {   //홈런
                    for (int b = 0; b < 3; b++) {
                        if (base[b]) {
                            totalScore++;
                            base[b] = false;
                        }
                    }
                    totalScore++;
                } else {    // 아웃
                    outs++;
                }

                playerNum = (playerNum == 9) ? 1 : playerNum + 1;
            }
        }

        answer = Math.max(answer, totalScore);
    }
}
