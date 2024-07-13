package baekjoon.그리디;

import java.util.PriorityQueue;
import java.util.Scanner;

// 백준 P1715
public class ex33_카드정렬하기 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int N = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for(int i = 0; i < N; i++){
            pq.offer(sc.nextInt());
        }

        int data1 = 0;
        int data2 = 0;
        int sum = 0;

        while (pq.size() != 1){
            data1 = pq.poll();
            data2 = pq.poll();

            int data3 = data1 + data2;

            sum += data3;
            pq.add(data3);
        }

        System.out.println(sum);
    }
}
