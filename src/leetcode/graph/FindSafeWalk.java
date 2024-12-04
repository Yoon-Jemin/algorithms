package leetcode.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class FindSafeWalk {

    public static void main(String[] args) {
        System.out.println(
                findSafeWalk(
                        List.of(
                                List.of(1, 1, 1),
                                List.of(1, 0, 0),
                                List.of(0, 1, 1),
                                List.of(0, 1, 1),
                                List.of(1, 1, 1)
                        ),
                        6
                )
        );
    }

    static int[] move_X = {-1, 1, 0, 0};
    static int[] move_Y = {0, 0, -1, 1};
    public static boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int max_X = grid.get(0).size();
        int max_Y = grid.size();

        boolean[][] visited = new boolean[max_Y][max_X];

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
        if (grid.get(0).get(0) == 0) queue.add(new int[]{0, 0, 0});
        else queue.add(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int now_X = now[0];
            int now_Y = now[1];
            int oneCount = now[2];

            if (oneCount >= health) continue;
            if (now_X == max_X - 1 && now_Y == max_Y - 1) return true;

            for (int i = 0; i < move_X.length; i++) {
                int next_X = now_X + move_X[i];
                int next_Y = now_Y + move_Y[i];

                if (next_X < 0 || next_X >= max_X || next_Y < 0 || next_Y >= max_Y || visited[next_Y][next_X]) continue;

                if (grid.get(next_Y).get(next_X) == 1) {
                    if (health > oneCount + 1){
                        queue.add(new int[]{next_X, next_Y, oneCount + 1});
                        visited[next_Y][next_X] = true;
                    }
                } else{
                    queue.add(new int[]{next_X, next_Y, oneCount});
                    visited[next_Y][next_X] = true;
                }
            }
        }

        return false;
    }
}
