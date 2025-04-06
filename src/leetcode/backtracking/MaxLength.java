package leetcode.backtracking;

import java.util.HashSet;
import java.util.List;

public class MaxLength {

    public static void main(String[] args) {
       List<String> arr = List.of("un", "iq", "ue");
        System.out.println(maxLength(arr));
    }

    public static int maxLength(List<String> arr) {
        return DFS(0, new HashSet<>(), arr);
    }

    private static int DFS(int index, HashSet<String> visited, List<String> arr) {
        int maxLength = visited.size();

        for (int i = index; i < arr.size(); i++) {
            Boolean flag = true;
            String next = arr.get(i);
            HashSet<String> copySet = new HashSet<>(visited);
            for (int j = 0; j < next.length(); j++) {
                String subString = next.substring(j, j + 1);
                if (copySet.contains(subString)) {
                    flag = false;
                    break;
                }
                copySet.add(subString);
            }
            if (flag) maxLength = Math.max(maxLength, DFS(i + 1, copySet, arr));
        }

        return maxLength;
    }
}
