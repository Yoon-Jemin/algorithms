package leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheckIfPrerequisite {

    public static void main(String[] args) {
        int numCourses = 5;
        int[][] prerequisites = {{0,1},{1,2},{2,3},{3,4}};
        int[][] queries = {{0,4},{4,0},{1,3},{3,0}};
        System.out.println(checkIfPrerequisite(numCourses, prerequisites, queries));
    }

    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        Boolean[][] map = new Boolean[numCourses][numCourses];
        for (int i = 0; i < numCourses; i++) {
            for (int j = 0; j < numCourses; j++) {
                if (i == j) map[i][j] = true;
                else map[i][j] = false;
            }
        }

        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[0]].add(prerequisite[1]);
        }

        for (int start = 0; start < numCourses; start++) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            while (!queue.isEmpty()) {
                int now = queue.poll();
                map[start][now] = true;
                for (int next : graph[now]) {
                    if (!map[start][next]) queue.add(next);
                }
            }
        }

        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries) {
            if(map[query[0]][query[1]]) res.add(true);
            else res.add(false);
        }

        return res;
    }
}
