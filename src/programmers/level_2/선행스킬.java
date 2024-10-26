package programmers.level_2;

import java.util.*;

public class 선행스킬 {

    public static void main(String[] args) {
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        String skill = "CBD";

        System.out.println(solution(skill, skill_trees));
    }

    public static int solution(String skill, String[] skill_trees) {
        int answer = 0;

        Set<String> set = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for(int i = skill.length() - 1; i >= 0; i--) {
            String s = skill.substring(i, i + 1);
            set.add(s);
            stack.push(s);
        }

        for(String skill_tree : skill_trees) {

            Stack<String> temp = new Stack<>();
            boolean flag = true;

            for(int i = 0; i < skill_tree.length(); i++) {
                String s = skill_tree.substring(i, i + 1);
                if(set.contains(s)) {
                    if(stack.peek().equals(s)) {
                        temp.add(stack.pop());
                    } else {
                        flag = false;
                    }
                }
            }

            while(!temp.isEmpty()) {
                stack.push(temp.pop());
            }

            if(flag) answer++;
        }

        return answer;
    }
}
