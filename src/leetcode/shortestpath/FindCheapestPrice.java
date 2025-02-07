package leetcode.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class FindCheapestPrice {

    public static void main(String[] args) {
        int n = 4;
        int[][] flights = {{0,1,100}, {1,2,100}, {2,0,100}, {1,3,600},{2,3,200}};
        int src = 0;
        int dst = 3;
        int k = 1;
        System.out.println(findCheapestPrice(n, flights, src, dst, k));
    }

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;

        for (int i = 0; i <= k; i++){
            int[] temp = prices.clone();

            for (int[] flight : flights) {
                int from = flight[0];
                int to = flight[1];
                int cost = flight[2];
                if (prices[from] != Integer.MAX_VALUE) {
                    temp[to] = Math.min(temp[to], prices[from] + cost);
                }
            }

            prices = temp;
        }

        if (prices[dst] != Integer.MAX_VALUE) return prices[dst];
        return -1;
    }
}
