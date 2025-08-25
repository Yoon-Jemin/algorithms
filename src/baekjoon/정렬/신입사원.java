package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 신입사원 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            List<int[]> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.add(new int[]{a, b});
            }

            Collections.sort(list, (a, b) -> Integer.compare(a[0], b[0]));

            int congrats = 1;
            int minInterview = list.get(0)[1];

            for (int i = 1; i < list.size(); i++) {
                int nextInterview = list.get(i)[1];
                if (nextInterview < minInterview) {
                    congrats++;
                    minInterview = nextInterview;
                }
            }

            System.out.println(congrats);
        }
    }
}
