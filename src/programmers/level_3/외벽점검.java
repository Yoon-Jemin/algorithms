package programmers.level_3;

import java.util.*;

public class 외벽점검 {

    public static void main(String[] args) {
        int n = 12;
        int[] week = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};

        System.out.println(solution(n, week, dist));
    }

    static int N, M, K;
    static int[] WEAK;
    static int[] DIST;
    static int answer = Integer.MAX_VALUE;
    public static int solution(int n, int[] weak, int[] dist) {
        N = n;  // 외벽의 둘레
        M = dist.length;    // 사람의 수
        K = weak.length;    // 취약한 지점의 개수
        DIST = dist;
        WEAK = new int[K * 2];

        for (int i = 0; i < K; i++) WEAK[i] = weak[i];
        for (int i = K; i < K * 2; i++) WEAK[i] = weak[i - K] + N;

        permute(new ArrayList<>(), new HashSet<>());

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static void permute(List<Integer> people, Set<Integer> visitedIdx) {
        if (people.size() == M) {
            for (int i = 0; i < WEAK.length / 2; i++) {
                calculate(i, people);
            }
            return;
        }

        for (int i = 0; i < M; i++) {
            int person = DIST[i];
            if (visitedIdx.contains(i)) continue;

            people.add(person);
            visitedIdx.add(i);
            permute(people, visitedIdx);
            people.remove(people.size() - 1);
            visitedIdx.remove(i);
        }
    }

    private static void calculate(int index, List<Integer> people) {
        int totalPeople = 0;
        int foundWeak = 0;
        int nowLocation = WEAK[index];
        int nextLocation = 0;

        for (int dist : people) {
            totalPeople++;
            nextLocation = nowLocation + dist;

            // 탐색 범위: nowLocation ~ nextLocation
            while (isSearch(nowLocation, nextLocation, index)) {
                foundWeak++;
                index++;

                // 다 찾은 경우
                if (foundWeak == K) {
                    answer = Math.min(answer, totalPeople);
                    return;
                }
            }

            nowLocation = WEAK[index];
        }
    }

    private static boolean isSearch(int nowLocation, int nextLocation, int index) {
        return nowLocation <= WEAK[index] && WEAK[index] <= nextLocation;
    }
}
