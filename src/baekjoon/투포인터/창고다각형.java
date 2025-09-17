package baekjoon.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 창고다각형 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int highestIdx = 0;
        int highestTop = 0;

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());   // 가로
            int b = Integer.parseInt(st.nextToken());   // 세로
            list.add(new int[]{a, b});

            if (b > highestTop) {
                highestIdx = a;
                highestTop = b;
            }
        }

        Collections.sort(list, (a, b) -> Integer.compare(a[0], b[0]));

        int left = 0;
        int leftIdx = list.get(0)[0];
        int leftTop = list.get(0)[1];

        int right = n - 1;
        int rightIdx = list.get(list.size() - 1)[0];
        int rightTop = list.get(list.size() - 1)[1];

        int answer = 0;

        while (leftIdx < highestIdx) {
            left++;
            int nextLeftIdx = list.get(left)[0];
            int nextLeftTop = list.get(left)[1];

            if (nextLeftTop >= leftTop) {
                answer += leftTop * (nextLeftIdx - leftIdx);
                leftIdx = nextLeftIdx;
                leftTop = nextLeftTop;
            }
        }

        while (highestIdx < rightIdx) {
            right--;
            int nextRightIdx = list.get(right)[0];
            int nextRightTop = list.get(right)[1];

            if (nextRightTop >= rightTop) {
                answer += rightTop * (rightIdx - nextRightIdx);
                rightIdx = nextRightIdx;
                rightTop = nextRightTop;
            }
        }

        System.out.println(answer + highestTop);
    }
}
