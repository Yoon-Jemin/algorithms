package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Partition {

    public static void main(String[] args) {
        String s = "aab";
        System.out.println(partition(s));
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backTrack(0, new ArrayList<String >(), s, res);
        return res;
    }

    private static void backTrack(int start, List<String> list, String s, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String sub = s.substring(start, end);
            if (isPalindrome(sub)) {
                list.add(sub);
                backTrack(end, list, s, res);
                list.remove(list.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
