package baekjoon.백트래킹;

import java.util.*;

public class N과M9 {

    static int[] nums;
    static int[] visited;
    static int[] answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 숫자의 개수
        int m = sc.nextInt();   // 숫자의 자리 수

        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        visited = new int[n];
        answer = new int[m];

        Arrays.sort(nums);
        backTrack(0, n, m);
    }

    private static void backTrack(int depth, int n, int m) {
        if (depth == m) {
            for (int i = 0; i < answer.length; i++) {
                System.out.print(answer[i] + " ");
            }
            System.out.println();
            return;
        }

        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0 && nums[i] != prev) {
                visited[i] = 1;
                answer[depth] = nums[i];
                prev = nums[i];
                backTrack(depth + 1, n, m);
                visited[i] = 0;
            }
        }
    }
}
