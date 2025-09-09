package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 크로스컨트리 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                int score = i;
                int team = Integer.parseInt(st.nextToken());

                if (!map.containsKey(team)) {
                    map.put(team, new ArrayList<>());
                    map.get(team).add(score);
                } else {
                    map.get(team).add(score);
                }
            }

            List<Integer> removeKeys = new ArrayList<>();
            for (int key : map.keySet()) {
                if (map.get(key).size() < 6) {
                    removeKeys.add(key);
                    continue;
                }
                Collections.sort(map.get(key));
            }

            for (int key : removeKeys) {
                map.remove(key);
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);   // {팀 : 점수}
            for (int key : map.keySet()) {
                for (int score : map.get(key)) {
                    pq.add(new int[]{key, score});
                }
            }

            int newScore = 1;
            Map<Integer, List<Integer>> map2 = new HashMap<>();
            while (!pq.isEmpty()) {
                int[] arr = pq.poll();
                int team = arr[0];
                int score = newScore;
                if (!map2.containsKey(team)) {
                    map2.put(team, new ArrayList<>());
                    map2.get(team).add(score);
                } else {
                    map2.get(team).add(score);
                }
                newScore++;
            }

            int winnerTeam = -1;
            int winnerTeamFifth = -1;
            int min = Integer.MAX_VALUE;
            for (int key : map2.keySet()) {
                int teamScore = getTeamScore(map2.get(key));
                if (teamScore < min) {
                    min = teamScore;
                    winnerTeam = key;
                    winnerTeamFifth = getFifthPlayer(map2.get(key));
                } else if (teamScore == min) {
                    if (getFifthPlayer(map2.get(key)) < winnerTeamFifth) {
                        winnerTeam = key;
                        winnerTeamFifth = getFifthPlayer(map2.get(key));
                    }
                }
            }

            System.out.println(winnerTeam);
        }
    }

    private static int getTeamScore(List<Integer> list) {
        return list.get(0) + list.get(1) + list.get(2) + list.get(3);
    }

    private static int getFifthPlayer(List<Integer> list) {
        return list.get(4);
    }
}
