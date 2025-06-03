package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불 {

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Queue<int[]> fireQ = new LinkedList<>();
        Queue<int[]> jihunQ = new LinkedList<>();
        int[][] fireMap = new int[n][m];
        int[][] fireVisited = new int[n][m];
        int[][] jihunMap = new int[n][m];
        int[][] jihunVisited = new int[n][m];

        char[][] map = new char[n][m];
        int startX = 0, startY = 0;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'F') {
                    fireQ.add(new int[]{i, j, 0});
                    fireMap[i][j] = 0;
                    fireVisited[i][j] = 1;
                }
                if (map[i][j] == 'J') {
                    startX = i;
                    startY = j;
                    jihunQ.add(new int[]{i, j, 0});
                    jihunMap[i][j] = 0;
                    jihunVisited[i][j] = 1;
                }
            }
        }

        if (startX == 0 || startX == n-1 || startY == 0 || startY == m-1) {
            System.out.println(1);
            return;
        }

        while (!fireQ.isEmpty()) {
            int[] now = fireQ.poll();
            int x = now[0];
            int y = now[1];
            int time = now[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == '#' || fireVisited[nx][ny] == 1) continue;
                fireQ.add(new int[]{nx, ny, time + 1});
                fireMap[nx][ny] = time + 1;
                fireVisited[nx][ny] = 1;
            }
        }

        while (!jihunQ.isEmpty()) {
            int[] now = jihunQ.poll();
            int x = now[0];
            int y = now[1];
            int time = now[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    System.out.println(time + 1);
                    return;
                }

                if (map[nx][ny] == '#' || jihunVisited[nx][ny] == 1) continue;
                if (fireVisited[nx][ny] == 0 || time + 1 < fireMap[nx][ny]) {
                    jihunQ.add(new int[]{nx, ny, time + 1});
                    jihunMap[nx][ny] = time + 1;
                    jihunVisited[nx][ny] = 1;
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}
