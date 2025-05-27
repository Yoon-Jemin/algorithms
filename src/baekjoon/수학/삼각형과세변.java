package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 삼각형과세변 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (int i = 0; i < 3; i++) pq.offer(Integer.parseInt(st.nextToken()));

            int a = pq.poll();
            int b = pq.poll();
            int c = pq.poll();

            if (a == 0) break;

            if (a >= b + c) {
                System.out.println("Invalid");
            } else if (a == c) {
                System.out.println("Equilateral");
            } else if (a == b || b == c) {
                System.out.println("Isosceles");
            } else {
                System.out.println("Scalene");
            }
        }
    }
}
