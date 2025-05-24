package baekjoon.정렬;

import java.util.Scanner;

public class 블로그 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   // 블로그를 시작하고 지난 일수
        int x = sc.nextInt();   // 윈도우 크기

        int[] views = new int[n];
        for (int i = 0; i < n; i++) {
            views[i] = sc.nextInt();
        }

        int candidate = 0;
        for (int i = 0; i < x; i++) {
            candidate += views[i];
        }

        int count = 1;
        int max = candidate;
        for (int i = 1; i + x - 1< n; i++) {
            candidate -= views[i - 1];
            candidate += views[i + x - 1];
            if (max == candidate) {
                count++;
            } else if (max < candidate) {
                max = candidate;
                count = 1;
            }
        }

        if (max == 0) System.out.println("SAD");
        else {
            System.out.println(max);
            System.out.println(count);
        }
    }
}
