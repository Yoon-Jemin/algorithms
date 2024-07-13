package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 백준 P1707
public class ex48_이분그래프 {
    static ArrayList<Integer>[] A;
    static int[] check;
    static boolean[] visited;
    static boolean isEven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int t = 0; t < testCase; t++){  // 테스트 케이스만큼 반복문을 돌림
            String[] s = br.readLine().split(" ");
            int V = Integer.parseInt(s[0]);     // 노드 개수
            int E = Integer.parseInt(s[1]);

            A = new ArrayList[V+1];
            check = new int[V+1];
            visited = new boolean[V+1];
            isEven = true;

            for(int i = 1; i <= V; i++){    // 인접 리스트 선언
                A[i] = new ArrayList<Integer>();
            }

            for(int i = 0; i < E; i++){     // 인접 리스트 초기화
                s = br.readLine().split(" ");
                int start = Integer.parseInt(s[0]);
                int end = Integer.parseInt(s[1]);

                // 방향이 없는 그래프
                A[start].add(end);
                A[end].add(start);
            }

            for(int i = 1; i <= V; i++){
                if(isEven) DFS(i);
                else break;
            }

            if(isEven) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    private static void DFS(int start) {
        visited[start] = true;
        for(int i : A[start]){  // 인접 리스트로 받아서 start에서 연결된 모든 노드
            if(!visited[i]){
                check[i] = (check[start] + 1) % 2;  // 바로 직전에 있는 노드와 다른 집합으로 분류
                DFS(i);
            } else if(check[start] == check[i]) isEven = false;
        }
    }
}
