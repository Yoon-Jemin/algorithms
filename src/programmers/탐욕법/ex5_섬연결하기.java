package programmers.탐욕법;

import java.util.*;

public class ex5_섬연결하기 {

    public static void main(String[] args) {

        int[][] costs = {{2, 3, 7}, {3, 6, 13}, {3, 5, 23}, {5, 6, 25}, {0, 1, 29}, {1, 5, 34}, {1, 2, 35}, {4, 5, 53}, {0, 4, 75}};
        int n = 7;

        System.out.println(solution(n, costs));

    }

    static int[] parent;
    public static int solution(int n, int[][] costs) {
        int answer = 0;
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        Queue<int[]> bridgeQueue = new LinkedList<>();
        bridgeQueue.addAll(Arrays.asList(costs));

        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }

        int usedEdge = 0;
        while (usedEdge < n-1){
            int[] nowBridge = bridgeQueue.poll();
            if(find(nowBridge[0]) != find(nowBridge[1])){
                union(nowBridge[0], nowBridge[1]);
                answer += nowBridge[2];
                usedEdge++;
            }
        }

        return answer;
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b){
            parent[b] = a;
        }
    }

    private static int find(int a) {
        if(a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }

}
