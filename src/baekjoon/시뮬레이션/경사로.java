package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 경사로 {

    static int N;
    static int L;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {   // 세로
            int[] path1 = new int[N];
            int[] path2 = new int[N];
            for (int j = 0; j < N; j++) {
                path1[j] = map[i][j];
                path2[j] = map[j][i];
            }
            if (isPossible(path1)) answer++;
            if (isPossible(path2)) answer++;
        }

        System.out.println(answer);
    }

    private static boolean isPossible(int[] path) {
        boolean[] visited = new boolean[N];
        Arrays.fill(visited, false);

        for (int i = 1; i < N; i++) {
            int before = path[i - 1];
            int now = path[i];

            if (before == now) continue;
            if (Math.abs(before - now) > 1) return false;

            if (before > now) {
                if (i + L - 1 > N - 1) return false;

                for (int next = 0; next < L; next++) {
                    if (visited[i + next] || path[i + next] != now) return false;
                }

                if (i + L < N - 1 && path[i + L] > now) return false;
                for (int k = 0; k < L; k++) visited[i + k] = true;

                i += L - 1;
            } else {
                if (i - L < 0) return false;

                for (int prev = 0; prev < L; prev++) {
                    if (visited[i - 1 - prev] || path[i - 1 - prev] != before) return false;
                }

                if (i - L - 1 > 0 && path[i - L - 1] > before) return false;
                for (int k = 0; k < L; k++) visited[i - 1 - k] = true;
            }
        }

        return true;
    }
}
