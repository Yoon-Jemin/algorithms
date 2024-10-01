package programmers.level_2;

import java.util.*;

public class 도넛과막대그래프 {

    public static void main(String[] args) {
        int[][] edges = {
                {2, 3}, {4, 3}, {1, 1}, {2, 1}
        };
        int[] result = solution(edges);
        for(int i=0; i<result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public static int[] solution(int[][] edges) {
        int createdNode = 0, donut = 0, bar = 0, eight = 0;

        int maxNode = 0;
        for(int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        ArrayList<Integer>[] nodes = new ArrayList[maxNode+1];  // maxNode + 1 = 12 (0 ~ 11)

        for(int i = 0; i <= maxNode; i++) {
            nodes[i] = new ArrayList<>();
        }

        int[] indegree = new int[maxNode+1];
        for(int[] edge : edges) {
            nodes[edge[0]].add(edge[1]);
            indegree[edge[1]]++;
        }

        int numGraph = 0;
        int max = 0;
        for(int i = 1; i <= maxNode; i++) {
            if(indegree[i] == 0 && nodes[i].size() > max) {
                createdNode = i;
                numGraph = nodes[i].size();
                max = nodes[i].size();
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[maxNode+1];
        for(Integer start : nodes[createdNode]) {
            int numNode = 1;
            int numEdge = 0;

            visited[start] = true;
            queue.add(start);
            while(!queue.isEmpty()) {
                int now = queue.poll();
                numEdge += nodes[now].size();
                for(Integer nextNode : nodes[now]) {
                    if(!visited[nextNode]) {
                        visited[nextNode] = true;
                        numNode++;
                        queue.add(nextNode);
                    }
                }
            }

            if(numNode == numEdge) donut++;
            else if(numNode - 1 == numEdge) bar++;
            else eight++;
        }

        return new int[]{createdNode, donut, bar, eight};
    }

}
