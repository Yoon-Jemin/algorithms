package programmers.깊이너비우선탐색;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ex5_아이템줍기 {
    public static void main(String[] args) {

        int[][] rectangle = {
                {1,1,7,4},
                {3,2,5,5},
                {4,3,6,9},
                {2,6,8,8}
        };

        System.out.println(solution(rectangle, 1, 3, 7, 8));

    }

    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[][] map;
    static boolean[][] visited;
    static int answer;
    public static int solution(int[][] rectangles, int characterX, int characterY, int itemX, int itemY) {
        answer = 0;
        map = new int[102][102];
        visited = new boolean[102][102];

        for(int i = 0; i < rectangles.length; i++) {
            int[] now = rectangles[i];
            for(int j = 0; j < now.length; j++) {
                now[j] *= 2;
            }
            makeLine(now);
        }

        answer = BFS(characterX * 2, characterY * 2, itemX * 2, itemY * 2);


        return answer;
    }

    private static int BFS(int cx, int cy, int ix, int iy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{cx, cy, 0});
        visited[cx][cy] = true;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if(now[0] == ix && now[1] == iy) return now[2]/2;
            for(int i = 0; i < dx.length; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(nx<0 || ny<0 || nx>=102 || ny>=102) continue;
                if(map[nx][ny] != 1 || visited[nx][ny]) continue;
                queue.add(new int[]{nx, ny, now[2] + 1});
                visited[nx][ny] = true;
            }
        }
        return 0;
    }

    private static void makeLine(int[] now) {
        for(int i = now[0]; i <= now[2]; i++) {
            for(int j = now[1]; j <= now[3]; j++) {
                if((i == now[0] || i == now[2] || j == now[1] || j == now[3]) && map[i][j] != 2) map[i][j] = 1;
                else map[i][j] = 2;
            }
        }
    }
}
