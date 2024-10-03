package programmers.level_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class 하노이탑 {

    public static void main(String[] args) {
        int[][] results = solution(3);
        for(int[] result : results ) {
            System.out.println(result[0] + " -> " + result[1]);
        }
    }

    private static List<int[]> ansList;
    public static int[][] solution(int n) {

        ansList = new ArrayList<>();

        dfs(n, 1, 3, 2);

        int[][] answer = new int[ansList.size()][];
        for(int i = 0; i < ansList.size(); i++) {
            answer[i] = ansList.get(i);
        }

        return answer;
    }

    private static void dfs(int n, int start, int to, int mid) {
        if(n == 1){
            ansList.add(new int[]{start, to});
            return;
        }

        dfs(n-1, start, mid, to);

        ansList.add(new int[]{start, to});

        dfs(n-1, mid, to, start);
    }
}
