package programmers.고득점Kit.깊이너비우선탐색;

import java.util.LinkedList;
import java.util.Queue;

public class ex2_네트워크 {

    public static void main(String[] args) {

        int n = 5;
        int[][] computers = {{1,1,0,1,0}, {1, 1, 0, 0, 0}, {0,0,1,0,1}, {1,0,0,1,1}, {0,0,1,1,1}};

        System.out.println(solution(n, computers));

    }

    static boolean[] visited;
    public static int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];

        for(int i = 0; i < n; i++){
            if(!visited[i]){
                answer++;
                BFS(computers,visited, i);
            }
        }

        return answer;
    }

    private static void BFS(int[][] computers, boolean[] visited, int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            int now = queue.poll();
            for(int i = 0; i < visited.length; i++){
                if( i != now && !visited[i] && computers[now][i] == 1){
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

    }
}
