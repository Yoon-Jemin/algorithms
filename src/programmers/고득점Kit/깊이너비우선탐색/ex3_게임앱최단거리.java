package programmers.고득점Kit.깊이너비우선탐색;

import java.util.LinkedList;
import java.util.Queue;

public class ex3_게임앱최단거리 {

    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        System.out.println(solution(maps));
    }

    static int[] moveX = { -1, 1, 0, 0};
    static int[] moveY = { 0, 0, -1, 1};
    static boolean[][] visited;
    static int[][] map;
    static int answer;
    static int max_X, max_Y;
    public static int solution(int[][] inputMap) {
        answer = -1;

        max_X = inputMap[0].length;
        max_Y = inputMap.length;

        map = new int[inputMap.length][inputMap[0].length];

        for(int i = 0; i < max_X; i++){
            for(int j = 0; j < max_Y; j++){
                map[j][i] = inputMap[j][i];
            }
        }

        visited = new boolean[max_Y][max_X];

        BFS(0,0);

        if(map[max_Y-1][max_X-1] == 1) return -1;
        return map[max_Y-1][max_X-1];
    }

    private static void BFS(int x, int y) {
        visited[y][x] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {x,y});
        while (!queue.isEmpty()){
            int now[] = queue.poll();
            for(int i = 0; i < 4; i++){
                int next_X = now[0] + moveX[i];
                int next_Y = now[1] + moveY[i];
                if(next_X >= 0 && next_Y >= 0 && next_X < max_X && next_Y < max_Y){
                    if(map[next_Y][next_X] != 0 && !visited[next_Y][next_X]){
                        visited[next_Y][next_X] = true;
                        map[next_Y][next_X] = map[now[1]][now[0]] + 1;
                        queue.offer(new int[] {next_X, next_Y});
                    }
                }
            }
        }
    }
}
