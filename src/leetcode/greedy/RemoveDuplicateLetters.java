package leetcode.greedy;

import java.util.*;

public class RemoveDuplicateLetters {

    public static void main(String[] args) {
//        System.out.println(removeDuplicateLetters("cbacdcbc"));
        System.out.println(removeDuplicateLetters("bcabc"));
    }

    public static String removeDuplicateLetters(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            lastIndex.put(c, i);
        }

        Set<Character> seen = new HashSet<>();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (seen.contains(c)) continue;

            while (!stack.isEmpty() && stack.peek() > c && lastIndex.get(stack.peek()) > i) {
                seen.remove(stack.pop());
            }

            stack.push(c);
            seen.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stack) sb.append(c);
        return sb.toString();
    }
}
