package leetcode.graph;

import java.util.*;

public class EquationsPossible {

    public static void main(String[] args) {
        String[] equations = {"c==c","f!=a","f==b","b==c"};
//        String[] equations = {"a==b","b!=a"};
        System.out.println(equationsPossible(equations));
    }

    public static boolean equationsPossible(String[] equations) {
        HashSet<String> set = new HashSet<>();

        Map<String, List<String>> graph = new HashMap<>();
        for (String e : equations) {
            String first = e.substring(0, 1);
            String second = e.substring(3);
            String equation = e.substring(1,3);

            if (first.equals(second)) {
                if (equation.equals("==")) continue;
                else return false;
            }

            if (equation.equals("==")) {
                List<String> firstList = graph.getOrDefault(first, new ArrayList<>());
                firstList.add(second);
                graph.put(first, firstList);

                List<String> secondList = graph.getOrDefault(second, new ArrayList<>());
                secondList.add(first);
                graph.put(second, secondList);

                set.add(first);
                set.add(second);
            }
        }

        int count = 0;
        HashSet<String> visited = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        for (String s : set) {
            if (visited.contains(s)) continue;
            DFS(s, graph, visited, map, count);
            count++;
        }

        // "!=" 일 경우 판단
        for (String e : equations) {
            String equation = e.substring(1,3);
            String first = e.substring(0, 1);
            String second = e.substring(3);
            if (equation.equals("==")) continue;

            if (set.contains(first) && set.contains(second)) {
                if (map.get(first) == map.get(second)) return false;
            }
        }

        return true;
    }

    private static void DFS(String s, Map<String, List<String>> graph, HashSet<String> visited, Map<String, Integer> map, int count) {
        visited.add(s);
        map.put(s,count);

        for (String next : graph.getOrDefault(s, new ArrayList<>())) {
            if (!visited.contains(next)) DFS(next, graph, visited, map, count);
        }
    }
}
