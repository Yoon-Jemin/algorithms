package baekjoon.동적계획법;

import java.util.*;

public class 일만들기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        Arrays.fill(arr,  -1);

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[]{n, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int num = poll[0];
            int dist = poll[1];

            if (arr[num] == -1) arr[num] = dist;
            else arr[num] = Math.min(arr[num], dist);

            if (num == 1) break;

            if (num % 3 == 0) queue.add(new int[] {num / 3, dist + 1});
            if (num % 2 == 0) queue.add(new int[] {num / 2, dist + 1});
            queue.add(new int[]{num - 1, dist + 1});
        }

        System.out.println(arr[1]);
    }
}
