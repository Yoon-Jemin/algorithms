package baekjoon.정렬;

import java.util.Arrays;
import java.util.Scanner;

public class 예산 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   // 지방의 수
        int[] money = new int[n];

        for (int i = 0; i < n; i++) {
            money[i] = sc.nextInt();
        }
        Arrays.sort(money);

        int totalMoney = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int averageMoney = totalMoney / (n - i);

            if (averageMoney >= money[i]) {
                totalMoney -= money[i];
            } else {
                System.out.println(averageMoney);
                return;
            }
        }

        System.out.println(money[n - 1]);
    }
}
