package programmers.level_3;

import java.util.PriorityQueue;

public class 경주로건설 {

    public static void main(String[] args) {
        int[][] board1 = {
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,0},
                {0,0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0,1},
                {0,0,1,0,0,0,1,0},
                {0,1,0,0,0,1,0,0},
                {1,0,0,0,0,0,0,0}
        };
        int[][] board2 = {
                {0,0,0},
                {0,0,0},
                {0,0,0},
        };
        int[][] board3 = {
                {0,0,1,0},
                {0,0,0,0},
                {0,1,0,1},
                {1,0,0,0}
        };
        System.out.println(solution(board1));
    }


    public static int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int[] moveX = {0, 0 ,1, -1};
        int[] moveY = {1, -1, 0, 0};

        int[][][] map = new int[n][m][4];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[3] - b[3]);    // x, y, 이전 방향, 비용


        if (board[0][1] == 0) {
            pq.offer(new int[]{0,1,0,100});
            map[0][1][0] = 100;
        }

        if (board[1][0] == 0) {
            pq.offer(new int[]{1,0,2,100});
            map[0][1][2] = 100;
        }

        while(!pq.isEmpty()) {
            int[] point = pq.poll();
            int x = point[0], y = point[1];
            int direction = point[2];
            int price = point[3];

            if (x == n - 1 && y == m - 1) {
                answer = price;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int newX = x + moveX[i];
                int newY = y + moveY[i];
                if (newX < 0 || newX >= n || newY < 0 || newY >= m || board[newX][newY] == 1) continue;

                int newPrice = price;
                if (i == direction || direction == -1) newPrice += 100;
                else newPrice += 600;

                if (newPrice > map[newX][newY][i] && map[newX][newY][i] != 0) continue;

                pq.offer(new int[]{newX, newY, i, newPrice});
                map[newX][newY][i] = newPrice;
            }
        }

        return answer;
    }
}
