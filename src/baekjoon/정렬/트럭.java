package baekjoon.정렬;

import java.util.*;

public class 트럭 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 트럭의 개수
        int bridge = sc.nextInt();  // 다리의 길이
        int limit = sc.nextInt();

        Queue<Integer> trucks = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            trucks.add(sc.nextInt());
        }

        int total = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < bridge; i++) {
            dq.add(0);
        }

        int time = 0;
        while (!dq.isEmpty()) {
            if (!trucks.isEmpty()) {
                if (total + trucks.peek() - dq.peekFirst() <= limit) {   // 다리에 진입 가능
                    int nextTruck = trucks.poll();
                    int out = dq.removeFirst();
                    dq.addLast(nextTruck);
                    total += nextTruck - out;
                } else {    // 다리에 새로운 차량 진입 불가능
                    dq.addLast(0);
                    total -= dq.removeFirst();
                }
            } else {
                dq.removeFirst();
            }
            time++;
        }

        System.out.println(time);
    }
}
