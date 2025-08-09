package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 치킨집 {

    public static int N, M;
    public static List<int[]> chicken;
    public static List<int[]> houses;
    public static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());   // 폐업시키지 않을 치킨집

        chicken = new ArrayList<>();
        houses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) houses.add(new int[]{i, j});
                if (num == 2) chicken.add(new int[]{i, j});
            }
        }

        comb(0, 0, new ArrayList<int[]>());
        System.out.println(answer);
    }

    private static void comb(int start, int depth, List<int[]> selected) {
        if (depth == M) {
            answer = Math.min(answer, calcDistance(selected));
            return;
        }
        for (int i = start; i < chicken.size(); i++) {
            selected.add(chicken.get(i));
            comb(i + 1, depth + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    private static int calcDistance(List<int[]> selected) {
        int sum = 0;

        for (int[] house : houses) {
            int distance = Integer.MAX_VALUE;
            for (int[] chicken : selected) {
                distance = Math.min(distance, Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]));
            }
            sum += distance;
        }

        return sum;
    }
}
