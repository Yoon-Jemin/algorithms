package programmers.level_2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 혼자놀기의달인 {

    public static void main(String[] args) {
        int[] cards = {8,6,3,7,2,5,1,4};
        System.out.println(solution(cards));
    }

    public static int solution(int[] cards) {
        Boolean[] isOpen = new Boolean[cards.length];
        Arrays.fill(isOpen, false);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 1; i <= cards.length; i++) {
            if(isOpen[i - 1]) continue;
            int count = DFS(i, cards, isOpen);
            pq.add(count);
        }

        int biggest = pq.poll();
        if (pq.isEmpty()) return 0;
        return biggest * pq.peek();
    }

    private static int DFS(int num, int[] cards, Boolean[] isOpen) {
        int count = 1;
        isOpen[num - 1] = true;

        if (!isOpen[cards[num - 1] - 1]) {
            count += DFS(cards[num-1], cards, isOpen);
        }

        return count;
    }
}
