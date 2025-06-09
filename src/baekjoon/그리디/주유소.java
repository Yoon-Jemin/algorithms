package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주유소 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 도시의 개수

        long[] gasPrice = new long[n];
        long[] distance = new long[n - 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            distance[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            gasPrice[i] = Long.parseLong(st.nextToken());
        }

        long totalCost = 0;
        long minPrice = gasPrice[0];

        for (int i = 0; i < n - 1; i++) {
            if (gasPrice[i] < minPrice) {
                minPrice = gasPrice[i];
            }

            totalCost += minPrice * distance[i];
        }

        System.out.println(totalCost);
    }
}
