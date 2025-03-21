package programmers.level_2;

import java.util.*;

public class 디펜스게임 {

    public int solution(int n, int k, int[] enemy) {
        if (enemy.length <= k) return enemy.length;

        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Comparator.reverseOrder());

        for (int attack : enemy) {
            pq.add(attack);

            if (n >= attack) {
                n -= attack;
            } else {    // 적의 수가 더 많음
                if (k > 0) {    // 무적권 사용
                    n += pq.poll();
                    k--;
                    n -= attack;
                } else {
                    break;
                }
            }
            answer++;
        }

        return answer;
    }
}
