package baekjoon.그래프;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 P1325
public class ex47_효율적인해킹 {
    static boolean[] visited;
    static ArrayList<Integer>[] A;
    static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 노드의 개수
        int M = Integer.parseInt(st.nextToken());   // 에지의 개수
        A = new ArrayList[N+1];
        answer = new int[N+1];

        for(int i = 1; i <= N; i++){
            A[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            A[S].add(E);
        }

        for(int i = 1; i <= N; i++){
            visited = new boolean[N+1];
            BFS(i);
        }

        int max = 0;
        for(int i = 1; i <= N; i++){
            max = Math.max(max, answer[i]);
        }

        for(int i = 1; i <= N; i++){
            if(answer[i] == max) System.out.print(i + " ");
        }
    }

    private static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(index);
        visited[index] = true;
        while (!queue.isEmpty()){
            int newNode = queue.poll();
            for(int i : A[newNode]){
                if(visited[i] == false){
                    visited[i] = true;
                    answer[i]++;
                    queue.offer(i);
                }
            }
        }
    }
}
