package programmers.level_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 석유시추 {

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{
                {1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 0 ,1, 0},
                {1, 0, 0, 0},
                {1, 1, 1, 1}
        }));
    }

    static boolean[][] visited;
    static int[][] map;
    static int[] moveX = {-1, 1, 0, 0};
    static int[] moveY = {0, 0, -1, 1};
    public static int solution(int[][] land) {

        map = new int[land.length][land[0].length];
        visited = new boolean[land.length][land[0].length];
        int[] drillList = new int[map[0].length];

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                visited[i][j] = false;
                map[i][j] = land[i][j];
            }
        }

        // BFS로 지도 업데이트
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                if(map[i][j] != 0 && !visited[i][j]) {
                    int sum = 1;
                    visited[i][j] = true;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});
                    int[] locations = new int[map[0].length];
                    while(!queue.isEmpty()) {
                        int[] now = queue.poll();
                        locations[now[1]] = 1;
                        for(int k = 0; k < 4; k++){
                            int nextY = now[0] + moveY[k];
                            int nextX = now[1] + moveX[k];
                            if(nextX >= 0 && nextY >= 0 && nextX < map[0].length && nextY < map.length && !visited[nextY][nextX] && map[nextY][nextX] != 0) {
                                queue.add(new int[]{nextY, nextX});
                                visited[nextY][nextX] = true;
                                sum++;
                            }
                        }
                    }
                    for(int a = 0; a < locations.length; a++) {
                        if(locations[a] == 1) {
                            drillList[a] += sum;
                        }
                    }
                }
            }
        }

        Arrays.sort(drillList);
        return drillList[drillList.length - 1];
    }

}
