package leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CountArrangement {

    public static void main(String[] args) {
        System.out.println(countArrangement(2));
    }

    public static int countArrangement(int n) {
        return DFS(n, new ArrayList<Integer>(), new HashSet<>());
    }

    private static int DFS(int n, ArrayList<Integer> arr, Set<Integer> visited) {
        if (arr.size() == n) {
            return 1;
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            int index = arr.size() + 1;
            if (visited.contains(i)) continue;
            if (i % index != 0 && index % i != 0) continue;

            visited.add(i);
            arr.add(i);
            count += DFS(n, arr, visited);
            arr.remove(arr.size() - 1);
            visited.remove(i);
        }

        return count;
    }
}
