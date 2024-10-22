package programmers.level_3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 양과늑대 {

    public static void main(String[] args) {
        int[] info = {0,1,0,1,1,0,1,0,0,1,0};
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}};
        System.out.println(solution(info, edges));
    }

    static int [] infos;
    static int [][] tree = new int[21][2];
    static int answer = 0;
    public static int solution(int[] info, int[][] edges) {
        infos = info;
        for(int i = 0; i <= 20; i++){
            Arrays.fill(tree[i], -1);
        }

        for(int i = 0; i < edges.length; i++){
            int parent = edges[i][0];
            int child = edges[i][1];
            if(tree[parent][0] == -1) tree[parent][0] = child;
            else tree[parent][1] = child;
        }

        HashSet<Integer> list = new HashSet<>();
        // 루트에서 시작
        list.add(0);
        search(1, 0, list);

        return answer;
    }

    private static void search(int sheepCount, int wolfCount, Set<Integer> list) {
        if(sheepCount <= wolfCount) return;
        answer = Math.max(answer, sheepCount);

        for(int cur : list){
            if(tree[cur][0] != -1 && !list.contains(tree[cur][0])){
                HashSet<Integer> set = new HashSet<>(list);
                set.add(tree[cur][0]);
                if(infos[tree[cur][0]] == 0){
                    search(sheepCount + 1, wolfCount, set);
                } else {
                    search(sheepCount, wolfCount + 1, set);
                }
            }

            if(tree[cur][1] != -1 && !list.contains(tree[cur][1])){
                HashSet<Integer> set = new HashSet<>(list);
                set.add(tree[cur][1]);
                if(infos[tree[cur][1]] == 0){
                    search(sheepCount + 1, wolfCount, set);
                } else {
                    search(sheepCount, wolfCount + 1, set);
                }
            }
        }

    }
}
