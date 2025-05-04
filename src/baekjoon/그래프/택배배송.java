package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 택배배송 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numHouse = Integer.parseInt(st.nextToken());
        int numRoad = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] graph = new ArrayList[numHouse+1];
        for (int i = 1; i <= numHouse; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= numRoad; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        int[] shortestPath = new int[numHouse+1];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);    // 현재 위치, 이동 거리
        pq.offer(new int[]{1,0});
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int now = poll[0];
            int distance = poll[1];

            if (now == numHouse) {
                System.out.println(distance);
                return;
            }

            for (int[] n : graph[now]) {
                int next = n[0];
                int nextDistance = n[1] + distance;
                if (shortestPath[next] > nextDistance) {
                    pq.offer(new int[]{next,nextDistance});
                    shortestPath[next] = nextDistance;
                }
            }
        }
    }
}
