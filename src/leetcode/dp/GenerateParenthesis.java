package leetcode.dp;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    public static void main(String[] args) {
        List<String> result =generateParenthesis(3);
        for (String s : result) {
            System.out.println(s);
        }
    }

    public static List<String> generateParenthesis(int n) {

        List<String>[] list = new List[n + 1];

        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        list[0].add("");
        list[1].add("()");
        if (n == 1) return list[1];

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                for (String left : list[j]) {
                    for (String right : list[i - j - 1]) {
                        list[i].add("(" + left + ")" + right);
                    }
                }
            }
        }

        return list[n];
    }
}
