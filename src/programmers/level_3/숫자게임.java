package programmers.level_3;

import java.util.*;

public class 숫자게임 {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5,1,3,7}, new int[]{2,2,6,8}));
    }

    public static int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        Deque<Integer> dequeA = new ArrayDeque<>();
        Deque<Integer> dequeB = new ArrayDeque<>();

        for (int i = 0; i < A.length; i++) {
            dequeA.add(A[i]);
            dequeB.add(B[i]);
        }

        int numA = dequeA.pollFirst();

        while (!dequeB.isEmpty()) {
            int numB = dequeB.pollFirst();
            if (numA < numB) {
                answer++;
                if(!dequeA.isEmpty()){
                    numA = dequeA.pollFirst();
                }
            }
        }

        return answer;
    }
}
