package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class SimplifyPath {

    public static void main(String[] args) {
//        System.out.println(simplifyPath("/home/user/Documents/../Pictures"));
//        System.out.println(simplifyPath("/a//b////c/d//././/.."));
        System.out.println(simplifyPath("/./"));
    }

    public static String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();

        String[] components = path.split("/");

        for (String component : components) {
            if (component.isEmpty() || component.equals(".")) continue;
            else if (component.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(component);
            }
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append("/");
            result.append(stack.pollLast());
        }

        if (result.isEmpty()) return "/";
        return result.toString();
    }
}
