package programmers.level_3;

import java.awt.color.ICC_ColorSpace;

public class 징검다리 {

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(solution(stones, k));
    }

    public static int solution(int[] stones, int k) {

        int left = 1;
        int right = -1;
        int answer = 0;

        for (int stone : stones) {
            right = Math.max(right, stone);
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canCross(stones, k , mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private static boolean canCross(int[] stones, int k, int people) {
        int jump = 0;

        for (int i = 0; i < stones.length; i++) {
            if (people > stones[i]) {
                jump++;
            } else {
                jump = 0;
            }
            if (jump >= k) {
                return false;
            }
        }

        return true;
    }
}
