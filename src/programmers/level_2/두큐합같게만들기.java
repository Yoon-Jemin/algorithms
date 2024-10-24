package programmers.level_2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class 두큐합같게만들기 {

    public static void main(String[] args) {
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};
        System.out.println(solution(queue1, queue2));
    }

    public static int solution(int[] queue1, int[] queue2) {

        int answer = 0;
        long sumA = 0;
        long sumB = 0;
        long target = 0;

        int limit = queue1.length * 2 + 1;

        Queue<Integer> queueA = new LinkedList<>();
        Queue<Integer> queueB = new LinkedList<>();
        for (int i = 0; i < queue1.length; i++) {
            sumA += queue1[i];
            sumB += queue2[i];
            queueA.add(queue1[i]);
            queueB.add(queue2[i]);
        }

        if ((sumA + sumB) % 2 != 0) return -1;

        target = (sumA + sumB) / 2;

        while (sumA != sumB) {
            if(answer > limit) return -1;
            if (sumA == target){
                break;
            } else if (sumA > target){
                int num = queueA.poll();
                queueB.add(num);
                sumA -= num;
                sumB += num;
            } else {
                int num = queueB.poll();
                queueA.add(num);
                sumA += num;
                sumB -= num;
            }
            answer++;
        }

        return answer;
    }
}
