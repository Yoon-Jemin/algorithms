package algorithms.그래프;

import java.util.Scanner;

// 백준 P1717
public class ex50_집합표현하기 {
    static int parent[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        parent = new int[N+1];

        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }

        for(int i = 0; i < M; i++){
            int question = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(question == 0){
                union(a,b);
            }else{
                if(checkSame(a,b)){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }

    private static boolean checkSame(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) return true;
        else return false;
    }

    private static void union(int a, int b) {
        // 대표 노드 찾아서 연결
        a = find(a);
        b = find(b);
        if(a != b){
            b = parent[a];
        }
    }

    private static int find(int a) {
        if(a == parent[a]){
            return a;
        } else{
            return parent[a] = find(parent[a]);     // 경로 압축!!!
        }
    }
}
