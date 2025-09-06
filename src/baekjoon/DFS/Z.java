package baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Z {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 2^n
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int answer = solve(n, r, c);
        System.out.println(answer);
    }

    private static int solve(int n, int r, int c) {
        if (n == 0) return 0;

        int half = 1 << (n - 1);
        int blockSize = half * half;

        if (r < half && c < half) {     // 제 1사분면
            return solve(n - 1, r, c);
        } else if (r < half && c >= half) {     // 제 2사분면
            return blockSize + solve(n - 1, r, c - half);
        } else if (r >= half && c < half) {     // 제 3사분면
            return 2 * blockSize + solve(n - 1, r - half, c);
        } else {    // 제 4사분면
            return 3 * blockSize + solve(n - 1, r - half, c - half);
        }
    }
}
