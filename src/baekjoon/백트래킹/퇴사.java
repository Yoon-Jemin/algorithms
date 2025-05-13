package baekjoon.백트래킹;

import java.util.Scanner;

public class 퇴사 {

    static int n;
    static int answer = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int[][] arr = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        int[] visited = new int[n + 1];
        backTrack(1, 0, arr, visited);
        System.out.println(answer);
    }

    public static void backTrack(int today, int time, int[][] arr, int[] visited) {
        answer = Math.max(answer, time);

        for (int i = today; i <= n; i++) {
            if (visited[i] == 1) continue;
            if (i + arr[i][0] > n + 1) continue;
            visited[i] = 1;
            backTrack(i + arr[i][0], time + arr[i][1], arr, visited);
            visited[i] = 0;
        }
    }
}
