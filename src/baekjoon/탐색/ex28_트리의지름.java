package baekjoon.탐색;

import java.util.*;

// 백준 P1167
public class ex28_트리의지름 {
    static ArrayList<Edge>[] A;
    static int[] distance;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 노드 개수

        A = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            A[i] = new ArrayList<Edge>();
        }

        for(int i = 0; i < N; i++){
            int S = sc.nextInt();
            while (true){
                int E = sc.nextInt();
                if(E == -1) break;

                int V = sc.nextInt();
                A[S].add(new Edge(E,V));
            }
        }

        distance = new int[N+1];
        visited = new boolean[N+1];
        BFS(1);
        int max = 1;

        for(int i = 2; i <= N; i++){
            if(distance[i] > distance[max]) max = i;
        }

        distance = new int[N+1];
        visited = new boolean[N+1];
        BFS(max);

        Arrays.sort(distance);
        System.out.println(distance[N]);

    }

    private static void BFS(int index) {
        Queue<Integer> myQueue = new LinkedList<Integer>();
        visited[index] = true;
        myQueue.add(index);

        while(!myQueue.isEmpty()){
            int now_node = myQueue.poll();
            for(Edge i : A[now_node]){
                int e = i.e;
                int v = i.value;
                if(!visited[e]){
                    visited[e] = true;
                    myQueue.add(e);
                    distance[e] = distance[now_node] + v;
                }
            }

        }
    }

    private static class Edge {
        int e;  // 목적지 노드
        int value;  // 에지의 가중치
        public Edge(int e, int value) {
            this.e = e;
            this.value = value;
        }
    }
}
