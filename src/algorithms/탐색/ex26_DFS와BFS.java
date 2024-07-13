package algorithms.탐색;

import java.util.*;

public class ex26_DFS와BFS {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 노드 개수
        int M = sc.nextInt();   // 에지 개수
        int start = sc.nextInt();   // 시작점

        A = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            A[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < M; i++){
            int S = sc.nextInt();
            int E = sc.nextInt();
            A[S].add(E);
            A[E].add(S);
        }

        for(int i = 1; i <= N; i++){
            Collections.sort(A[i]);
        }

        visited = new boolean[N+1];
        DFS(start);
        System.out.println();
        visited = new boolean[N+1];
        BFS(start);

    }
    private static void DFS(int node) {
        System.out.print(node + " ");
        visited[node] = true;
        for(int i : A[node]){
            if(!visited[i]) DFS(i);
        }
    }

    private static void BFS(int node) {
        Queue<Integer> myQueue = new LinkedList<Integer>();
        myQueue.add(node);
        visited[node] = true;
        while (!myQueue.isEmpty()){
            int now_node = myQueue.poll();
            System.out.print(now_node + " ");
            for(int i : A[now_node]){
                if(!visited[i]){
                    myQueue.add(i);
                    visited[i] = true;
                }
            }
        }
    }


}
