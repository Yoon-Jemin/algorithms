package baekjoon.탐색;

import java.util.Scanner;

public class 날짜계산 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int earth = sc.nextInt();   // 1 ~ 15
        int sun = sc.nextInt();     // 1 ~ 28
        int moon = sc.nextInt();    // 1 ~ 19

        int year = 1;
        int e = 1;
        int s = 1;
        int m = 1;

        while (e != earth || s != sun || m != moon) {
            e++;
            s++;
            m++;
            year++;

            if (e == 16) e = 1;
            if (s == 29) s = 1;
            if (m == 20) m = 1;
        }

        System.out.println(year);
    }
}
