package programmers.탐욕법;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ex3_큰수만들기 {
    public static void main(String[] args) {
        String number = "9876543214";

//        System.out.println(number.substring(0,4));
        System.out.println(solution(number, 4));
    }
    public static String solution(String number, int k) {
        String answer = "";
        int size = number.length();

        List<Integer> listNumber = new ArrayList<>();
        for(int i = 0; i < size; i++){
            listNumber.add(Character.getNumericValue(number.charAt(i)));
        }

        Stack<Integer> stack = new Stack<>();

        int delete = 0;
        for(int num : listNumber){
            if(stack.isEmpty()) stack.push(num);
            else if(delete < k){
                while (!stack.isEmpty() && stack.peek() < num){
                    stack.pop();
                    delete++;
                    if(delete == k) break;
                }
                stack.push(num);
            } else if(delete == k){
                stack.push(num);
            }
        }

        while (!stack.isEmpty()){
            if(stack.size() > size - k) stack.pop();
            answer = stack.pop() + answer;
        }

        return answer;
    }
}
