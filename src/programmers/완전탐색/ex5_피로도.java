package programmers.완전탐색;

import java.util.ArrayList;
import java.util.List;

public class ex5_피로도 {
    public static void main(String[] args) {
        int[][] dungeons = {{80,20},{50,40},{30,10}};
        System.out.println(solution(80, dungeons));
    }
    public static int solution(int k, int[][] dungeons) {
        int answer = 0;
        int max = 0;

        List<int[][]> permutations = generatePermutations(dungeons);

        for(int[][] permutation : permutations){
            int stamina = k;
            answer = 0;
            for(int i = 0; i < permutation.length; i++){
                if(stamina >= permutation[i][0]){
                    stamina -= permutation[i][1];
                    answer++;
                } else {
                    break;
                }
            }
            max = Math.max(max,answer);
        }

        return max;
    }

    private static List<int[][]> generatePermutations(int[][] dungeons) {
        List<int[][]> result = new ArrayList<>();
        permute(dungeons, 0, result);
        return result;
    }

    private static void permute(int[][] dungeons, int start, List<int[][]> result) {
        if (start >= dungeons.length) {
            // Create a copy of the current permutation and add it to the result list
            int[][] copy = new int[dungeons.length][];
            for (int i = 0; i < dungeons.length; i++) {
                copy[i] = dungeons[i].clone();
            }
            result.add(copy);
            return;
        }

        for (int i = start; i < dungeons.length; i++) {
            swap(dungeons, start, i);
            permute(dungeons, start + 1, result);
            swap(dungeons, start, i); // backtrack
        }
    }

    private static void swap(int[][] dungeons, int i, int j) {
        int[] temp = dungeons[i];
        dungeons[i] = dungeons[j];
        dungeons[j] = temp;
    }
}
