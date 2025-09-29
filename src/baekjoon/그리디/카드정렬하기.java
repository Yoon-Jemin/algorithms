package baekjoon.그리디;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 카드정렬하기 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(sc.nextInt());
        }

        if (pq.size() == 1) {
            System.out.println(0);
            return;
        }

        int sum = 0;

        while (true) {
            int num1 = pq.poll();
            int num2 = pq.poll();

            sum += num1 + num2;
            if (pq.isEmpty()) break;
            pq.add(num1 + num2);
        }

        System.out.println(sum);
    }
}
