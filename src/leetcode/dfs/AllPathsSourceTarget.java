package leetcode.dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllPathsSourceTarget {

    public static void main(String[] args) {
        int[][] graph = {{4,3,1},{3,2,4},{3},{4},{}};
        System.out.println(allPathsSourceTarget(graph));
    }

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();

        DFS(0, new ArrayList<>(), new HashSet<>(), graph, res);

        return res;
    }

    public static void DFS(int curr, List<Integer> path, Set<Integer> visited, int[][] graph, List<List<Integer>> res) {
        visited.add(curr);
        path.add(curr);

        if (curr == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int next : graph[curr]) {
            if (!visited.contains(next)) {
                DFS(next, path, visited, graph, res);
                visited.remove(next);
                path.remove(path.size() - 1);
            }
        }
    }
}
