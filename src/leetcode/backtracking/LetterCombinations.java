package leetcode.backtracking;

import java.util.*;

public class LetterCombinations {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return new ArrayList<>();

        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        Set<String> res = new HashSet<>();
        DFS(0, "", digits, map, res);
        return new ArrayList<>(res);
    }

    private static void DFS(int index, String temp, String digits, Map<Integer, String> map, Set<String> res) {
        if (temp.length() == digits.length()) {
            res.add(temp);
            return;
        }

        String strNum = map.get(digits.charAt(index) - '0');
        for (int i = 0; i < strNum.length(); i++) {
            temp += strNum.charAt(i);
            DFS(index + 1, temp, digits, map, res);
            temp = temp.substring(0, temp.length() - 1);
        }
    }
}
