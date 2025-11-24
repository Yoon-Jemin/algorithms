package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 수업 {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            pq.add(new int[] {height, k});
        }

        TreeMap<Integer, Integer> teams = new TreeMap<>();

        while (!pq.isEmpty()) {
            int[] student = pq.poll();
            int k = student[1];

            Integer teamSize = teams.floorKey(k - 1);

            if (teamSize == null) {
                teams.put(1, teams.getOrDefault(1, 0) + 1);
            } else {
                int count = teams.get(teamSize);
                if (count == 1) {
                    teams.remove(teamSize);
                } else {
                    teams.put(teamSize, count - 1);
                }
                teams.put(teamSize + 1, teams.getOrDefault(teamSize + 1, 0) + 1);
            }
        }

        int totalTeams = 0;
        for (int count : teams.values()) {
            totalTeams += count;
        }

        System.out.println(totalTeams);
    }
}
