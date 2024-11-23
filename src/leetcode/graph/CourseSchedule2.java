package leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule2 {

    public static void main(String[] args) {
        int n = 2;
        int[][] prerequisites = new int[][]{
                {1, 0}, {2, 0}, {3, 1}, {3, 2}
        };
        System.out.println(findOrder(n, prerequisites));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int start = prerequisite[0];
            int end = prerequisite[1];
            graph.get(start).add(end);
            inDegree[end]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> route = new ArrayList<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            route.add(cur);

            for (int neighbor : graph.get(cur)) {
                inDegree[neighbor]--;
                if(inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (route.size() == numCourses) {
            int[] result = new int[route.size()];
            int j = route.size() - 1;
            for (int i = 0; i < route.size(); i++) {
                result[j] = route.get(i);
                j--;
            }
            return result;
        }

        return new int[] {};
    }
}
