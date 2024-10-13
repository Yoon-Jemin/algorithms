package programmers.level_2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class N개의최소공배수 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2,6,8,14}));
    }

    public static int solution(int[] arr) {
        int answer = 0;

        Arrays.sort(arr);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }

        int num1 = 0;
        int num2 = 0;
        num1 = queue.poll();
        while (!queue.isEmpty()) {
            num2 = queue.poll();

            int bigNum = Math.max(num1, num2);
            int smallNum = Math.min(num1, num2);
            int lcm = bigNum;
            while(lcm % smallNum != 0) {
                lcm += bigNum;
            }

            num1 = lcm;
        }

        return num1;
    }
}
