package baekjoon.바킹독;

import java.util.*;

public class 숨바꼭질 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        int[] distance = new int[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);    // 현재 위치, 거리
        pq.offer(new int[]{1, 0});
        Set<Integer> visited = new HashSet<>();
        visited.add(1);

        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            int nowNode = poll[0];
            int nowDistance = poll[1];
            distance[nowNode] = nowDistance;

            for (int nextNode : graph[nowNode]) {
                if (!visited.contains(nextNode)) {
                    pq.offer(new int[]{nextNode, nowDistance + 1});
                    visited.add(nextNode);
                }
            }
        }

        int minIndex = -1;
        int maxDistance = -1;
        int maxCount = 0;
        for (int i = 1; i <= n; i++) {
            if (distance[i] > maxDistance) {    // 갱신
                minIndex = i;
                maxDistance = distance[i];
                maxCount = 1;
            } else if (distance[i] == maxDistance) {
                maxCount++;
            }
        }

        System.out.println(minIndex + " " +maxDistance + " " +  maxCount);
    }
}
