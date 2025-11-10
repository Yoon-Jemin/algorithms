package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 돌그룹 {

    static int A, B, C;
    public static void main(String[] args) throws IOException {
        // 크기가 같지 않은 두 그룹을 고름
        // 돌의 개수가 작은 쪽을 X, 큰 쪽을 Y
        // X에 있는 돌의 개수를 X+X개, Y에 있는 돌의 개수를 Y-X개

        // 돌을 같은 개수로 만들 수 있으면 1을, 아니면 0
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int sum = A + B + C;

        if (sum % 3 != 0) {
            System.out.println(0);
            return;
        }

        int[][] visited = new int[1501][1501];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {A, B});
        visited[A][B] = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            int x = now[0];
            int y = now[1];
            int z = sum - x - y;

            if (x == y && y == z) {
                System.out.println(1);
                return;
            }

            int[][] pairs = {{x, y}, {x, z}, {y, z}};
            for (int[] pair : pairs) {
                int nx = pair[0];
                int ny = pair[1];

                if (nx == ny) continue;

                if (nx < ny) {
                    ny = ny - nx;
                    nx = nx + nx;
                } else {
                    nx = nx - ny;
                    ny = ny + ny;
                }

                int nz = sum - nx - ny;
                int[] next = {nx, ny, nz};
                Arrays.sort(next);
                nx = next[0];
                ny = next[1];

                if (visited[nx][ny] == 0) {
                    visited[nx][ny] = 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        System.out.println(0);
    }
}
