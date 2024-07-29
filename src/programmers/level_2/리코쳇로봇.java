package programmers.level_2;

import java.util.LinkedList;
import java.util.Queue;

public class 리코쳇로봇 {

    public static void main(String[] args) {

        String[] board = {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};

        System.out.println(solution(board));

    }
    static int [] arx = {-1, 1, 0, 0};
    static int [] ary = {0, 0, -1, 1};
    static int row;
    static int col;
    static int [][] map;
    static int start_x, start_y, end_x, end_y;
    public static int solution(String[] board) {
        row = board.length;
        col = board[0].length();

        map = new int[row][col];
        for(int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                char cur = board[i].charAt(j);

                if(cur == 'R'){
                    start_x = i;
                    start_y = j;
                }

                if(cur == 'G'){
                    end_x = i;
                    end_y = j;
                }

                if(cur == 'D'){
                    map[i][j] = -1;
                } else {
                    map[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        BFS();

        if(map[end_x][end_y] == Integer.MAX_VALUE) return -1;

        return map[end_x][end_y];

    }

    private static void BFS() {
        map[start_x][start_y] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start_x, start_y});

        while (!q.isEmpty()){
            int [] cur = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];

            for (int i = 0; i < 4; i++){
                int nx = cur_x + arx[i];
                int ny = cur_y + ary[i];

                while (validation(nx, ny) && map[nx][ny] != -1){
                    nx += arx[i];
                    ny += ary[i];
                }

                nx -= arx[i];
                ny -= ary[i];

                if(nx == cur_x && ny == cur_y) continue;
                if(map[nx][ny] <= map[cur_x][cur_y] + 1) continue;

                map[nx][ny] = map[cur_x][cur_y] + 1;
                q.add(new int[]{nx, ny});
            }
        }
    }

    private static boolean validation(int nx, int ny) {
        if(0 <= nx && 0 <= ny && nx < row && ny < col) return true;
        return false;
    }
}
