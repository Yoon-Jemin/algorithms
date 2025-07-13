package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int[] stocks = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                stocks[j] = Integer.parseInt(st.nextToken());
            }

            long profit = 0;

            for (int now = m - 1; now >= 1; now--) {
                int nowPrice = stocks[now];

                for (int next = now - 1; next >= 0; next--) {
                    int nextPrice = stocks[next];
                    if (nowPrice >= nextPrice) {
                        profit += nowPrice - nextPrice;
                        now--;
                    } else {
                        break;
                    }
                }
            }

            System.out.println(profit);
        }
    }
}
