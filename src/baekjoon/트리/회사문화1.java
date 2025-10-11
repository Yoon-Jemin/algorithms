package baekjoon.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 회사문화1 {

    static ArrayList<Integer>[] graph;
    static int[] employees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 회사의 직원 수
        int m = Integer.parseInt(st.nextToken());   // 칭찬의 횟수

        st = new StringTokenizer(br.readLine());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int root = 0;
        for (int junior = 1; junior <= n; junior++) {
            int senior = Integer.parseInt(st.nextToken());

            if (senior == -1) {
                root = junior;
                continue;
            }

            graph[senior].add(junior);
        }

        employees = new int[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int employeeNum = Integer.parseInt(st.nextToken());     // 칭찬을 받은 직원 번호
            int score = Integer.parseInt(st.nextToken());   // 칭찬 수치

            employees[employeeNum] += score;
        }

        DFS(root);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < employees.length; i++) {
            sb.append(employees[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static void DFS(int now) {
        for (int next : graph[now]) {
            employees[next] += employees[now];
            DFS(next);
        }
    }
}
