package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 백준 P11724
public class ex23_연결요소의개수 {
    static boolean[] visited;
    static ArrayList<Integer>[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 노드의 개수
        int m = Integer.parseInt(st.nextToken());   // 엣지의 개수

        A = new ArrayList[n+1];     // 그래프 데이터 저장 인접 리스트
        visited = new boolean[n+1];     // 방문 기록 저장 배열

        for(int i = 1; i <= n; i++){
            A[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            A[start].add(end);
            A[end].add(start);
        }

        int count = 0;
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                count++;
                DFS(i);
            }
        }
        System.out.println(count);
    }

    private static void DFS(int v) {
        if(visited[v]) return;
        visited[v] = true;
        for (int node : A[v]){
            if(!visited[node]) DFS(node);
        }
    }
}
