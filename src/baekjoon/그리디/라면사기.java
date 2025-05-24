package baekjoon.그리디;

import java.util.Scanner;

public class 라면사기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   // 라면 공장의 개수
        int[] noodle = new int[n];

        for (int i = 0; i < n; i++) {
            noodle[i] = sc.nextInt();
        }

        int price = 0;
        for (int i = 0; i < n; i++) {

            if (i + 2 < n) {
                if (noodle[i + 1] > noodle[i + 2]) {
                    int min = Math.min(noodle[i], noodle[i + 1] - noodle[i + 2]);
                    price += min * 5;
                    noodle[i] -= min;
                    noodle[i + 1] -= min;
                }
                int min = Math.min(Math.min(noodle[i], noodle[i + 1]), noodle[i + 2]);
                price += min * 7;
                noodle[i] -= min;
                noodle[i + 1] -= min;
                noodle[i + 2] -= min;
            }

            if (i + 1 < n) {
                int min = Math.min(noodle[i], noodle[i + 1]);
                price += min * 5;
                noodle[i] -= min;
                noodle[i + 1] -= min;
            }

            if (noodle[i] > 0) {
                price += noodle[i] * 3;
                noodle[i] = 0;
            }
        }

        System.out.println(price);
    }
}
