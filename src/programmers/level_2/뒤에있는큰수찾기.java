package programmers.level_2;

import java.util.Stack;

public class 뒤에있는큰수찾기 {

    public static void main(String[] args) {
        int[] numbers = {9, 1, 5, 3, 6, 2};
        int[] result = solution(numbers);
        for(int num : result) {
            System.out.println(num);
        }
    }

    // [2, 3, 3, 5]	-> [3, 5, 5, -1]
    // [9, 1, 5, 3, 6, 2] -> [-1, 5, 6, 6, -1, -1]

    public static int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i = 0; i < numbers.length; i++) { answer[i] = -1;}

        Stack<Integer> stack = new Stack<>();

        for(int i = numbers.length - 1; i >= 0; i--) {
            int num = numbers[i];   // 비교해야야 하는 숫자

            while (!stack.isEmpty()){
                if(stack.peek() > num) {
                    answer[i] = stack.peek();
                    break;
                } else {
                    stack.pop();
                }
            }
            stack.push(num);
        }

        return answer;
    }
}
