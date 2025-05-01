package leetcode.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindRepeatedDnaSequences {

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> result = findRepeatedDnaSequences(s);
        System.out.println(result);
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) return List.of();

        Set<String> set = new HashSet<>();
        Set<String> result = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (i + 10 > s.length()) break;
            if (set.contains(s.substring(i, i + 10))) {
                result.add(s.substring(i, i + 10));
            } else {
                set.add(s.substring(i, i + 10));
            }
        }
        return new ArrayList<>(result);
    }
}
