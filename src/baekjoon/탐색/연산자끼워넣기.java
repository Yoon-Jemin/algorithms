package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연산자끼워넣기 {

    static int N;
    static int[] operators;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        operators = new int[N - 1];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            int oper = Integer.parseInt(st.nextToken());
            while (oper > 0) {
                operators[index] = i;
                oper--;
                index++;
            }
        }

        boolean[] visited = new boolean[N - 1];
        backTrack(0, new ArrayList<>(), queue, visited);
        System.out.println(max);
        System.out.println(min);
    }

    private static void backTrack(int depth, List<Integer> list, Queue<Integer> queue, boolean[] visited) {
        if (depth == N - 1) {
            int sum = calculate(list, queue);
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < operators.length; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            list.add(operators[i]);
            backTrack(depth + 1, list, queue, visited);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    private static int calculate(List<Integer> list, Queue<Integer> queue) {
        Queue<Integer> copyQueue = new LinkedList<>();
        copyQueue.addAll(queue);

        int index = 0;

        int num1 = copyQueue.poll();
        while (!copyQueue.isEmpty()) {
            int num2 = copyQueue.poll();
            int operator = list.get(index);

            if (operator == 0) num1 = plus(num1, num2);
            else if (operator == 1) num1 = minus(num1, num2);
            else if (operator == 2) num1 = multiply(num1, num2);
            else if (operator == 3) num1 = divide(num1, num2);
            index++;
        }

        return num1;
    }

    private static int plus(int num1, int num2) {
        return num1 + num2;
    }

    private static int minus(int num1, int num2) {
        return num1 - num2;
    }

    private static int multiply(int num1, int num2) {
        return num1 * num2;
    }

    private static int divide(int num1, int num2) {
        int firstNum = Math.abs(num1);
        int secondNum = Math.abs(num2);

        int answer = firstNum / secondNum;

        if (num1 > 0 && num2 < 0) {
            return answer * -1;
        } else if (num1 < 0 && num2 > 0) {
            return answer * -1;
        }

        return answer;
    }
}
