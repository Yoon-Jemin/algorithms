package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 새로운게임2 {

    static class Horse {
        int x;
        int y;
        int direction;

        Horse(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        private void changeDirection() {
            if (direction == 1) direction = 2;
            else if (direction == 2) direction = 1;
            else if (direction == 3) direction = 4;
            else if (direction == 4) direction = 3;
        }
    }

    static int N, K;
    static int[][] color;
    static List<Integer>[][] board;
    static Horse[] horses;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static boolean gameOver = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());   // 말의 개수
        color = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        board = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                board[i][j] = new ArrayList<>();
            }
        }

        horses = new Horse[K + 1];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            horses[i] = new Horse(x, y, direction);
            board[x][y].add(i);
        }

        int turn = 0;
        while (turn < 1000) {
            turn++;
            for (int i = 1; i <= K; i++) {
                simulate(i);
                if (gameOver) {
                    System.out.println(turn);
                    return;
                }
            }
        }

        System.out.println(-1);
    }

    private static void simulate(int horseNum) {
        Horse horse = horses[horseNum];

        // 같이 움직일 말 있는지 찾기
        List<Integer> boardStatus = board[horse.x][horse.y];
        List<Integer> horsesToMove = new ArrayList<>();
        boolean carry = false;
        for (int i = 0; i < boardStatus.size(); i++) {
            if (carry) {
                horsesToMove.add(boardStatus.get(i));
                continue;
            }

            if (boardStatus.get(i) == horseNum) {
                horsesToMove.add(boardStatus.get(i));
                carry = true;
            }
        }

        // 다음 칸으로 이동
        int nx = horse.x + dx[horse.direction];
        int ny = horse.y + dy[horse.direction];

        if (nx < 1 || ny < 1 || nx > N || ny > N || color[nx][ny] == 2) {   // 체스판을 벗어 나거나 파란색인 경우
            horse.changeDirection();
            nx = horse.x + dx[horse.direction];
            ny = horse.y + dy[horse.direction];

            if (nx >= 1 && ny >= 1 && nx <= N && ny <= N && color[nx][ny] != 2) {
                if (color[nx][ny] == 0) moveWhite(horsesToMove, horse.direction);
                if (color[nx][ny] == 1) moveRed(horsesToMove, horse.direction);
            } else return;
        } else if (color[nx][ny] == 0) {    // 다음 칸이 흰색 칸
            moveWhite(horsesToMove, horse.direction);
        } else if (color[nx][ny] == 1) {    // 다음 칸이 빨간색 칸
            moveRed(horsesToMove, horse.direction);
        }
    }

    private static void moveWhite(List<Integer> horsesToMove, int direction) {
        Horse base = horses[horsesToMove.get(0)];
        int nx = base.x + dx[direction];
        int ny = base.y + dy[direction];

        // 기존 칸에서 제거
        board[base.x][base.y].removeAll(horsesToMove);

        // 그대로 순서 유지해서 새로운 칸에 추가
        board[nx][ny].addAll(horsesToMove);

        // 새로운 칸으로 이동
        for (int horse : horsesToMove) {
            horses[horse].x = nx;
            horses[horse].y = ny;
        }

        if (board[nx][ny].size() >= 4) gameOver = true;
    }

    private static void moveRed(List<Integer> horsesToMove, int direction) {
        Horse base = horses[horsesToMove.get(0)];
        int nx = base.x + dx[direction];
        int ny = base.y + dy[direction];

        // 기존 칸에서 제거
        board[base.x][base.y].removeAll(horsesToMove);

        for (int i = horsesToMove.size() - 1; i >= 0; i--) {
            int horse = horsesToMove.get(i);
            board[nx][ny].add(horse);
            horses[horse].x = nx;
            horses[horse].y = ny;
        }

        if (board[nx][ny].size() >= 4) gameOver = true;
    }
}
