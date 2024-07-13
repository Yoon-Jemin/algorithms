package baekjoon.그리디;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

// 백준 P1744
public class ex34_수묶기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 카드 묶음의 개수

        PriorityQueue<Integer> plusPq = new PriorityQueue<Integer>(Collections.reverseOrder());
        PriorityQueue<Integer> minusPq = new PriorityQueue<Integer>();

        int one = 0;
        int zero = 0;
        int sum = 0;

        for(int i = 0; i < N; i++){
            int num = sc.nextInt();

            if(num > 1) plusPq.offer(num);
            else if (num == 0) zero++;
            else if (num == 1) one++;
            else minusPq.offer(num);
        }

        while (plusPq.size() > 2){
            int data1 = plusPq.poll();
            int data2 = plusPq.poll();
            sum += (data1 * data2);
        }
        while (!plusPq.isEmpty()){
            sum += plusPq.poll();
        }

        while (minusPq.size() > 2){
            int data1 = minusPq.poll();
            int data2 = minusPq.poll();
            sum += (data1*data2);
        }

        while (!minusPq.isEmpty()){
            int data = minusPq.poll();
            if(zero > 0){
                data = 0;
                zero--;
            }
            sum += data;
        }

        sum += one;
        System.out.println(sum);
    }
}
