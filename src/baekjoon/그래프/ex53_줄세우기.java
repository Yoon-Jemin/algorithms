package baekjoon.그래프;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//백준 P2252
public class ex53_줄세우기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();   // 학생 수 (노드 개수)
        int M = sc.nextInt();   // 비교 데이터 (에지 개수)
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            A.add(new ArrayList<>());
        }
        int indegree[] = new int [N + 1];

        for(int i = 0; i < M; i++){
            int start = sc.nextInt();
            int end = sc.nextInt();
            A.get(start).add(end);
            indegree[end]++;    // 진입차수 배열 데이터 저장 부분
        }

        //위상 정렬 실행
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i <= N; i++){
            if(indegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()){
            int now = queue.poll();
            System.out.print(now + " ");
            for (int next : A.get(now)){
                indegree[next]--;
                if(indegree[next] == 0) queue.offer(next);
            }
        }
    }
}
