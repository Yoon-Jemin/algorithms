package leetcode.backtracking;

import java.util.*;

public class DistributeCookies {

    public static void main(String[] args) {
//        int[] cookies = {6,1,3,2,2,4,1,2};
//        int k = 3;
        int[] cookies = {11, 6, 14, 4};
        int k = 2;
        System.out.println(distributeCookies(cookies, k));
    }

    public static int distributeCookies(int[] cookies, int k) {
        int[] distribute = new int[k];
        return backTrack(distribute, 0,  cookies, k, Integer.MAX_VALUE);
    }

    private static int backTrack(int[] distribute, int cur, int[] cookies, int k, int minUnfairness) {
        if (cur >= cookies.length) {
            int max = 0;
            for (int child : distribute) {
                max = Math.max(max, child);
            }
            return Math.min(minUnfairness, max);
        }

        int result = minUnfairness;
        for (int i = 0; i < k; i++) {
            distribute[i] += cookies[cur];

            if (distribute[i] < result) {
                result = Math.min(result, backTrack(distribute, cur + 1, cookies, k, result));
            }
            distribute[i] -= cookies[cur];
        }

        return result;
    }
}
