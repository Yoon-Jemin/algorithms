package baekjoon.이진검색트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 홍익투어리스트 {

    static int N, Q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 구역의 개수
        Q = Integer.parseInt(st.nextToken());   // 쿼리의 개수
        TreeSet<Integer> set = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        int[] places = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            places[i] = Integer.parseInt(st.nextToken());
            if (places[i] == 1) set.add(i);
        }

        int dohyun = 1;
        for (int j = 0; j < Q; j++) {
            st = new StringTokenizer(br.readLine());

            int q = Integer.parseInt(st.nextToken());

            if (q == 1) {   // 1 -> i번 구역이 명소가 아니었다면 명소로 지정되고, 명소였다면 지정이 풀리게 된다
                int i = Integer.parseInt(st.nextToken());
                if (set.contains(i)) set.remove(i);
                else set.add(i);
            } else if (q == 2) {    // 2 -> 도현이가 시계방향으로 x 만큼 이동
                int x = Integer.parseInt(st.nextToken());
                dohyun = (dohyun + x) % N == 0 ? N : (dohyun + x) % N;
            } else if (q == 3) {    // 3 -> 도현이가 명소에 도달하기 위해 시계방향으로 최소 몇 칸 움직여야 하는 지 (명소가 없으면 -1)
                if (set.isEmpty()) {
                    System.out.println(-1);
                } else {
                    Integer next = set.ceiling(dohyun);
                    if (next != null) {
                        System.out.println(next - dohyun);
                    } else {
                        Integer first = set.first();
                        System.out.println(N - dohyun + first);
                    }
                }
            }
        }
    }
}
