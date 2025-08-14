package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 택배 {

    public static class Box {
        int start;
        int end;
        int amount;

        public Box(int start, int end, int amount) {
            this.start = start;
            this.end = end;
            this.amount = amount;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 마을의 개수
        int limit = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        List<Box> boxes = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());
            boxes.add(new Box(start, end, amount));
        }

        int[] remains = new int[N];
        Arrays.fill (remains, limit);

        boxes.sort((a, b) -> {
            if (a.end == b.end) return a.start - b.start;
            return a.end - b.end;
        });

        int answer = 0;
        for (Box box : boxes) {
            int minCap = Integer.MAX_VALUE;
            for (int i = box.start; i < box.end; i++) {
                minCap = Math.min(minCap, remains[i]);
            }
            int load = Math.min(minCap, box.amount);
            for (int i = box.start; i < box.end; i++) {
                remains[i] -= load;
            }
            answer += load;
        }

        System.out.println(answer);
    }
}
