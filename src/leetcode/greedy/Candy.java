package leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Candy {

    public static void main(String[] args) {
        int[] ratings = {1,3,4,5,2};
        System.out.println(candy(ratings));
    }

    public static int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < ratings.length; i++) {
            pq.offer(new int[]{ratings[i], i});
        }

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int nowRating = now[0];
            int nowIndex = now[1];


            if (nowIndex != 0) {
                int neighbor = nowIndex - 1;
                if (nowRating > ratings[neighbor]) candies[nowIndex] = candies[neighbor] + 1;
            }
            if (nowIndex != ratings.length - 1) {
                int neighbor = nowIndex + 1;
                if (nowRating > ratings[neighbor]) candies[nowIndex] = Math.max(candies[nowIndex], candies[neighbor] + 1);
            }
        }

        int sum = 0;
        for (int i = 0; i < ratings.length; i++) {
            sum += candies[i];
        }
        return sum;
    }
}
