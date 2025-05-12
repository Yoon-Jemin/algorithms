package programmers.level_2;

import java.util.LinkedList;
import java.util.Queue;

public class 프렌즈4블록 {

    public static void main(String[] args) {
//        int m = 6;
//        int n = 6;
//        String[] board = {
//                "TTTANT",
//                "RRFACC",
//                "RRRFCC",
//                "TRRRAA",
//                "TTMMMF",
//                "TMMTTJ"
//        };
        int m = 4;
        int n = 5;
        String[] board = {
                "CCBDE", "AAADE", "AAABF", "CCBBF"
        };
        System.out.println(solution(m, n, board));
    }

    public static int solution(int m, int n, String[] board) {
        int answer = 0;

        String[][] map = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = board[i].charAt(j) + "";
            }
        }

        while (true) {
            boolean isErased = false;
            int[][] checkMap = new int[m][n];
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (map[i][j] != "-" && isFour(i,j,map)) {
                        checkMap[i][j] = 1;
                        checkMap[i][j+1] = 1;
                        checkMap[i+1][j] = 1;
                        checkMap[i+1][j+1] = 1;
                        isErased = true;
                    }
                }
            }

            if (!isErased) break;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (checkMap[i][j] == 1) {
                        answer++;
                        map[i][j] = "-";
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                Queue<String> queue = new LinkedList<>();
                for (int i = m -1; i >= 0; i--) {
                    if (!map[i][j].equals("-")) {
                        queue.add(map[i][j]);
                    }
                }

                for (int i = m - 1; i >= 0; i--) {
                    if (queue.size() > 0) {
                        map[i][j] = queue.poll();
                    } else {
                        map[i][j] = "-";
                    }
                }
            }

        }

        return answer;
    }

    private static boolean isFour(int i, int j, String[][] map) {
        boolean flag1 = map[i][j].equals(map[i][j+1]);
        boolean flag2 = map[i][j].equals(map[i+1][j]);
        boolean flag3 = map[i][j].equals(map[i+1][j+1]);

        return flag1 && flag2 && flag3;
    }
}
