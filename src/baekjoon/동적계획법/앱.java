package baekjoon.동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 앱 {

    public static void main(String[] args) throws IOException {     // todo: 다시 풀기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // N개의 앱
        int M = Integer.parseInt(st.nextToken());   // M 바이트 확보

        st = new StringTokenizer(br.readLine());
        int[] usedMemory = new int[N];
        for (int i = 0; i < N; i++) {
            usedMemory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] cost = new int[N];
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[100001];     // 비용을 C 썻을 때, 확보할 수 있는 최대 메모리
        int sumCost = 0;
        for (int i = 0; i < N; i++) {
            sumCost += cost[i];
            for (int c = sumCost; c >= cost[i]; c--) {
                dp[c] = Math.max(dp[c], dp[c - cost[i]] + usedMemory[i]);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int c = 0; c <= sumCost; c++) {
            if (dp[c] >= M) {
                answer = c;
                break;
            }
        }

        System.out.println(answer);
    }
}
