package baekjoon.조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 스타트와링크 {

    static int N;
    static int[][] chart;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        chart = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                chart[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] selected = new boolean[N];
        Arrays.fill(selected, false);
        selected[0] = true;

        dfs(1, 1, selected);
        System.out.println(answer);
    }

    private static void dfs(int depth, int selectedCount, boolean[] selected) {
        if (selectedCount == N/2) {
            calculate(selected);
            return;
        }

        for (int i = depth; i < N; i++) {
            selected[i] = true;
            dfs(i + 1, selectedCount + 1, selected);
            selected[i] = false;
        }
    }

    private static void calculate(boolean[] selected) {
        List<Integer> startTeam = new ArrayList<>();
        List<Integer> linkTeam = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (selected[i]) startTeam.add(i);
            else linkTeam.add(i);
        }

        int startTeamScore = getScore(startTeam);
        int linkTeamScore = getScore(linkTeam);

        answer = Math.min(answer, Math.abs(startTeamScore - linkTeamScore));
    }

    private static int getScore(List<Integer> list) {
        int score = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                score += chart[list.get(i)][list.get(j)];
                score += chart[list.get(j)][list.get(i)];
            }
        }
        return score;
    }
}
