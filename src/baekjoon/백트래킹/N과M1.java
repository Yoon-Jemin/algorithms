package baekjoon.백트래킹;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class N과M1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 숫자 범위
        int m = sc.nextInt();   // 몇 자리 수 인지

        BackTrack(1, new HashSet<>(), new ArrayList<>(), n, m);
    }

    private static void BackTrack(int start, HashSet<Integer> visited, ArrayList<Integer> list, int n, int m) {
        if (list.size() == m) {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }

        for (int i = 1; i <= n; i++) {
            if (visited.contains(i)) continue;
            list.add(i);
            visited.add(i);
            BackTrack(start + 1, visited, list, n, m);
            list.remove(list.size() - 1);
            visited.remove(i);
        }
    }
}
