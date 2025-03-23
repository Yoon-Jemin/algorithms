package leetcode.dfs;

public class CountBattleShips {
    public int countBattleships(char[][] board) {
        int answer = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    answer++;
                    DFS(i, j, board);
                }
            }
        }

        return answer;
    }

    private void DFS(int x, int y, char[][] board) {
        board[x][y] = '.';

        int[] move_x = {0, 0, -1, 1};
        int[] move_y = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++){
            int next_x = x + move_x[i];
            int next_y = y + move_y[i];

            if (next_x < 0 || next_y < 0 || next_x >= board.length || next_y >= board[0].length) continue;
            if (board[next_x][next_y] == 'X') DFS(next_x, next_y, board);
        }
    }
}
