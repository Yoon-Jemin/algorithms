package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MaxProfitAssignment {

    public static void main(String[] args) {
        int[] difficulty = {2,4,6,8,10};
        int[] profit = {10,20,30,40,50};
        int[] worker = {4,5,6,7};
        System.out.println(maxProfitAssignment(difficulty, profit, worker));
    }

    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        Arrays.sort(worker);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {    // 수익 : 난이도
            if (a[0] != b[0]) {
                return b[0] - a[0]; // 수익 기준 내림 차순
            } else {
                return a[1] - b[1]; // 난이도 기준 오름 차순
            }
        });

        for (int i = 0; i < profit.length; i++) {
            pq.offer(new int[]{profit[i], difficulty[i]});
        }

        List<int[]> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            list.add(pq.poll());
        }

        int answer = 0;
        for (int maxWork : worker) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)[1] <= maxWork) {
                    answer += list.get(i)[0];
                    break;
                }
            }
        }

        return answer;
    }
}
