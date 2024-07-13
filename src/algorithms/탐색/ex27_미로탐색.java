package algorithms.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준  P2178
public class ex27_미로탐색 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] A;
    static boolean[][] visited;

    static int n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        A = new int[n][m];
        visited = new boolean[n][m];

        for(int i =0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int j =0; j < m; j++){
                A[i][j] = Integer.parseInt(line.substring(j,j+1));
            }
        }

        BFS(0,0);
        System.out.println(A[n-1][m-1]);
    }

    private static void BFS(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {i,j});
        visited[i][j] = true;
        while (!queue.isEmpty()){
            int[] now = queue.poll();
            for(int k = 0; k < 4; k++){
                int x = now[0] + dx[k];
                int y = now[1] + dy[k];
                if( x >= 0 && y >=0 && x < n && y < m){     // 좌표 유효성 검사
                    if(visited[x][y] == false && A[x][y] != 0 ){
                        visited[x][y] = true;
                        A[x][y] = A[now[0]][now[1]] + 1;
                        queue.add(new int[]{x,y});
                    }
                }
            }
        }
    }
}
