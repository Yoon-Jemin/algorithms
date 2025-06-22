package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {

    public static void main(String[] args) {
        String s = "101023";
        System.out.println(restoreIpAddresses(s));
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backTrack(0, new ArrayList<>(), res, s);
        return res;
    }

    private static void backTrack(int index, List<String> candidate, List<String> res, String s) {
        if (candidate.size() == 4) {
            if (index == s.length()) {
                res.add(String.join(".", candidate));
            }
            return;
        }

        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {
            String part = s.substring(index, index + len);
            if (checkNum(part)) {
                candidate.add(part);
                backTrack(index + len, candidate, res, s);
                candidate.remove(candidate.size() - 1);
            }
        }
    }

    private static boolean checkNum(String strNum) {
        if (strNum == null || strNum.length() == 0) return false;
        else if (strNum.length() == 1) return true;
        else if (strNum.charAt(0) == '0') return false;

        int num = Integer.parseInt(strNum);
        if (num < 0 || num > 255) return false;
        return true;
    }
}
