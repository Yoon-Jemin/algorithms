package baekjoon.DFS;

import java.util.*;

public class 물통 {

    static int A, B, C;
    static int[][][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();
        visited = new int[A + 1][B + 1][C + 1];

        int a = 0;
        int b = 0;
        int c = C;

        Set<Integer> set = new HashSet<>();
        DFS(a, b, c, set);

        List<Integer> answer = new ArrayList<>(set);
        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        for (int ans : answer) sb.append(ans).append(" ");
        System.out.println(sb.toString());
    }

    public static void DFS(int a, int b, int c, Set<Integer> set) {
        if (visited[a][b][c] == 1) return;
        visited[a][b][c] = 1;

        if (a == 0) {
            set.add(c);
        }

        if (a > 0) {
            // a -> b
            int na1 = a, nb1 = b, nc1 = c;
            if (a >= B - b) {
                na1 -= (B - b);
                nb1 = B;
            } else {
                na1 = 0;
                nb1 += a;
            }
            DFS(na1, nb1, nc1, set);

            // a -> c
            int na2 = a, nb2 = b, nc2 = c;
            if (a >= C - c) {
                na2 -= (C - c);
                nc2 = C;
            } else {
                na2 = 0;
                nc2 += a;
            }
            DFS(na2, nb2, nc2, set);
        }

        if (b > 0) {
            // b -> a
            int na1 = a, nb1 = b, nc1 = c;
            if (b >= A - a) {
                nb1 -= (A - a);
                na1 = A;
            } else {
                nb1 = 0;
                na1 += b;
            }
            DFS(na1, nb1, nc1, set);

            // b -> c
            int na2 = a, nb2 = b, nc2 = c;
            if (b >= C - c) {
                nb2 -= (C - c);
                nc2 = C;
            } else {
                nb2 = 0;
                nc2 += b;
            }
            DFS(na2, nb2, nc2, set);
        }

        if (c > 0) {
            // c -> a
            int na1 = a, nb1 = b, nc1 = c;
            if (c >= A - a) {
                nc1 -= (A - a);
                na1 = A;
            } else {
                nc1 = 0;
                na1 += c;
            }
            DFS(na1, nb1, nc1, set);

            // c -> b
            int na2 = a, nb2 = b, nc2 = c;
            if (c >= B - b) {
                nc2 -= (B - b);
                nb2 = B;
            } else {
                nc2 = 0;
                nb2 += c;
            }
            DFS(na2, nb2, nc2, set);
        }
    }
}
