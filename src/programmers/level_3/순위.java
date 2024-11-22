package programmers.level_3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class 순위 {

    public static void main(String[] args) {
        int n = 5;
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};

        System.out.println(solution(n, results));
    }

    public static int solution(int n, int[][] results) {
        int answer = 0;
        int[][] graph = new int[n+1][n+1];

        for (int[] result : results) {
            int win = result[0];
            int lose = result[1];
            graph[win][lose] = 1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][k] + graph[k][j] == 2) {
                        graph[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if(graph[i][j] == 1 || graph[j][i] == 1) count++;
            }
            if (count == n - 1) answer++;
        }

        return answer;
    }

}
