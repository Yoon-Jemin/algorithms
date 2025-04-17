package baekjoon.백트래킹;

import java.util.ArrayList;
import java.util.Scanner;

public class N과M2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 숫자 범위
        int m = sc.nextInt();   // 숫자 자리 수

        BackTrack(1, new ArrayList<>(), n, m);
    }

    private static void BackTrack(int start, ArrayList<Integer> list, int n, int m) {
        if (list.size() == m) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }

        for (int i = start; i <= n; i++) {
            list.add(i);
            BackTrack(i + 1, list, n, m);
            list.remove(list.size() - 1);
        }
    }
}
