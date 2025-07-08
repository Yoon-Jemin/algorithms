package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 스타트링크 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 건물의 높이
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int up = Integer.parseInt(st.nextToken());
        int down = Integer.parseInt(st.nextToken());

        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});     // 현재 위치, 이동 횟수
        visited.add(start);

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int now = poll[0];
            int count = poll[1];

            if (now == end) {
                System.out.println(count);
                return;
            }

            if (now + up <= n && !visited.contains(now + up)) {
                queue.add(new int[]{now + up, count + 1});
                visited.add(now + up);
            }
            if (now - down >= 1 && !visited.contains(now - down)) {
                queue.add(new int[]{now - down, count + 1});
                visited.add(now - down);
            }
        }

        System.out.println("use the stairs");
    }
}
