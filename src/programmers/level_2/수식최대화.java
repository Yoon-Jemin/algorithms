package programmers.level_2;

import java.util.ArrayList;

// todo: 복습 필요
public class 수식최대화 {

    public static void main(String[] args) {
        System.out.println(solution("100-200*300-500+20"));
    }

    static ArrayList<Long> list = new ArrayList<>();
    static ArrayList<String> cal = new ArrayList<>();
    static String[] sequence = new String[3];
    static String[] calculation = {"*", "+", "-"};
    static boolean[] visited = new boolean[3];
    public static long answer = 0;
    public static long solution(String expression) {
        init(expression);
        combination(0);
        return answer;
    }

    private static void combination(int depth) {
        if(depth == 3){
            calculate();
            return;
        }
        for(int i = 0; i < 3; i++){
            if(visited[i]) continue;
            visited[i] = true;
            sequence[depth] = calculation[i];
            combination(depth + 1);
            visited[i] = false;
        }
    }

    private static void calculate() {
        ArrayList<Long> nums = new ArrayList<>(list);
        ArrayList<String> cals = new ArrayList<>(cal);

        while (!cals.isEmpty()) {
            int idx = -1;
            String target = null;
            if(idx == -1){
                idx = cals.indexOf(sequence[0]);
                target = sequence[0];
            }
            if(idx == -1){
                idx = cals.indexOf(sequence[1]);
                target = sequence[1];
            }
            if(idx == -1){
                idx = cals.indexOf(sequence[2]);
                target = sequence[2];
            }
            if(idx == -1) break;

            long tar1 = nums.get(idx);
            long tar2 = nums.get(idx + 1);

            cals.remove(idx);
            nums.remove(idx + 1);

            long result = 0;

            if(target.equals("*")){
                result = tar1 * tar2;
            } else if (target.equals("+")) {
                result = tar1 + tar2;
            } else {
                result = tar1 - tar2;
            }
            nums.set(idx, result);
        }

        long sum = 0;
        for(int i = 0; i < nums.size(); i++){
            sum += nums.get(i);
        }

        answer = Math.max(Math.abs(sum), answer);
    }

    public static void init(String expression) {
        int index = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if(c == '*' || c == '+' || c == '-') {
                list.add(Long.parseLong(expression.substring(index, i)));
                cal.add(String.valueOf(c));
                index = i + 1;
            }
        }
        list.add(Long.parseLong(expression.substring(index, expression.length())));
    }
}
