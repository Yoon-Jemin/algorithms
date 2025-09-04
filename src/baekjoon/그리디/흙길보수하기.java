package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 흙길보수하기 {

    static int lastCovered = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 물웅덩이의 개수
        int l = Integer.parseInt(st.nextToken());   // 널빤지의 길이

        List<int[]> holes = new ArrayList<>();
        int length = 0;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            holes.add(new int[]{a, b});
            length = Math.max(length, b);
        }

        Collections.sort(holes, (a, b) -> a[0] - b[0]);
        int answer = 0;

        for (int[] hole : holes) {
            int start = hole[0];
            int end = hole[1] - 1;

            if (lastCovered >= end) continue;

            if (lastCovered < start) {
                answer += cover(start, end, l);
            } else if (lastCovered >= start) {
                answer += cover(lastCovered + 1, end, l);
            }
        }

        System.out.println(answer);
    }

    private static int cover(int start, int end, int length) {
        int used = 0;

        if ((end - start + 1) % length == 0) used = (end - start + 1) / length;
        else used = (end - start + 1) / length + 1;

        lastCovered = (start - 1) + length * used;

        return used;
    }
}
