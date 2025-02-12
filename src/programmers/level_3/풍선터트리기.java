package programmers.level_3;

import java.util.PriorityQueue;

public class 풍선터트리기 {

    public static void main(String[] args) {
        int[] a = {9,-1,-5};
        System.out.println(solution(a));
    }

    public static int solution(int[] a) {
        int answer = 0;
        int n = a.length;

        int[] left = new int[n];
        left[0] = a[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.min(left[i-1], a[i]);
        }

        int[] right = new int[n];
        right[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i+1], a[i]);
        }

        for (int i = 0; i < n; i++) {
            if (a[i] <= left[i] || a[i] <= right[i]) answer++;
        }

        return answer;
    }
}
