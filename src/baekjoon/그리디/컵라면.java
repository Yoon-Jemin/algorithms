package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 컵라면 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int ramen = Integer.parseInt(st.nextToken());
            list.add(new int[]{deadline, ramen});
        }

        Collections.sort(list, (a, b) -> a[0] - b[0]);

        int time = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < list.size(); i++) {
            int[] arr = list.get(i);
            int deadline = arr[0];
            int ramen = arr[1];

            if (queue.isEmpty() || queue.size() < deadline) {
                queue.add(ramen);
            } else {
                if (ramen > queue.peek()) {
                    queue.add(ramen);
                    queue.poll();
                }
            }
        }

        int total = 0;
        while (!queue.isEmpty()) {
            total += queue.poll();
        }
        System.out.println(total);
    }
}
