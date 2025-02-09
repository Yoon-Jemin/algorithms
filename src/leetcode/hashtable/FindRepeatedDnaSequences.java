package leetcode.hashtable;

import java.util.*;

public class FindRepeatedDnaSequences {

    public static void main(String[] args) {
        System.out.println(findRepeatedDnaSequences("AAAAAAAAAAA"));
//        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();

        Set<String> set = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (int i = 0; i <= n - 10; i++) {
            String dna = s.substring(i, i + 10);
            if (set.contains(dna)) {
                res.add(dna);
            } else {
                set.add(dna);
            }
        }

        return new ArrayList<>(res);
    }
}