package baekjoon.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 가장가까운공통조상 {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int numNodes = Integer.parseInt(st.nextToken());
            ArrayList<Integer>[] graph = new ArrayList[numNodes + 1];
            for (int i = 1; i <= numNodes; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < numNodes - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[b].add(a);
            }

            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            Set<Integer> node1Parents = new HashSet<>();
            while (!graph[node1].isEmpty()) {
                node1Parents.add(node1);
                node1 = graph[node1].get(0);
            }
            node1Parents.add(node1);

            while (!node1Parents.contains(node2)) {
                node2 = graph[node2].get(0);
            }

            System.out.println(node2);
        }
    }
}
