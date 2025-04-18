package baekjoon.백트래킹;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class 근손실 {

    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 날짜
        int loss = sc.nextInt();

        int[] kit = new int[n];
        for (int i = 0; i < n; i++) {
            kit[i] = sc.nextInt();
        }

        backTrack(500, new ArrayList<>(), new HashSet<>(), n, loss, kit);
        System.out.println(answer);
    }

    private static void backTrack(int weight, List<Integer> list, HashSet<Integer> visited, int n, int loss, int[] kit) {
        if (list.size() == n) {
            answer++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited.contains(i)) continue;

            int nextWeight = weight + kit[i] - loss;
            if (nextWeight < 500) continue;
            list.add(i);
            visited.add(i);
            backTrack(nextWeight, list, visited, n, loss, kit);
            list.remove(list.size() - 1);
            visited.remove(i);
        }
    }
}
