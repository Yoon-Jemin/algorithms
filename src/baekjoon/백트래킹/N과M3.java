package baekjoon.백트래킹;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class N과M3 {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   // 숫자 범위
        int m = sc.nextInt();   // 숫자 자릿수

        BackTrack(new ArrayList<>(), n, m);
        System.out.println(sb);
    }

    private static void BackTrack(List<Integer> list, int n, int m) {
        if (list.size() == m) {
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i)).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= n; i++) {
            list.add(i);
            BackTrack(list, n, m);
            list.remove(list.size() - 1);
        }
    }
}
