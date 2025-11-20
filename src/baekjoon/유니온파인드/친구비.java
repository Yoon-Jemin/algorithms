package baekjoon.유니온파인드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 친구비 {

    static int N, M, K;
    static Set<Integer> friends;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 학생 수
        M = Integer.parseInt(st.nextToken());   // 친구관계 수
        K = Integer.parseInt(st.nextToken());   // 가지고 있는 돈

        st = new StringTokenizer(br.readLine());
        int[] fee = new int[N + 1];
        friends = new HashSet<>();
        graph = new ArrayList[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 1; i <= N; i++) {
            fee[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
            pq.add(new int[] {i, fee[i]});
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }

        int spent = 0;
        while (!pq.isEmpty() && friends.size() < N) {
            int[] friend = pq.poll();

            int friendNum = friend[0];
            int cost = friend[1];

            if (friends.contains(friendNum)) continue;
            if (spent + cost > K) break;

            DFS(friendNum);
            spent += cost;
        }

        if (friends.size() == N) System.out.println(spent);
        else System.out.println("Oh no");
    }

    private static void DFS(int now) {
        friends.add(now);

        for (int next : graph[now]) {
            if (friends.contains(next)) continue;

            DFS(next);
        }
    }
}
