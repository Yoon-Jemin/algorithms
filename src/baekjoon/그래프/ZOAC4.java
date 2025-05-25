package baekjoon.그래프;

import java.util.Scanner;

public class ZOAC4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int distanceN = sc.nextInt();
        int distanceM = sc.nextInt();

        int countN = n / (distanceN + 1);
        if (n % (distanceN + 1) != 0) countN++;

        int countM = m / (distanceM + 1);
        if (m % (distanceM + 1) != 0) countM++;

        System.out.println(countN * countM);
    }
}
