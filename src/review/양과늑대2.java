package review;

import java.util.Arrays;
import java.util.HashSet;

public class 양과늑대2 {

    public static void main(String[] args) {
        int[] info = {0,1,0,1,1,0,1,0,0,1,0};
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}};
        System.out.println(solution(info, edges));
    }

    static int[] infos;
    static int[][] tree;
    static int answer = 0;
    public static int solution(int[] info, int[][] edges) {
        infos = info;
        tree = new int[infos.length][2];

        for(int i = 0; i < infos.length; i++){
            Arrays.fill(tree[i], -1);
        }

        for(int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            if(tree[parent][0] == -1){
                tree[parent][0] = child;
            } else {
                tree[parent][1] = child;
            }
        }

        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        search(1, 0 ,set);

        return answer;
    }

    public static void search(int sheepCount, int wolfCount, HashSet<Integer> set){
        if(sheepCount == wolfCount){
            return;
        }
        answer = Math.max(answer, sheepCount);

        for(int cur : set){
            if(tree[cur][0] != -1 && !set.contains(tree[cur][0])){
                HashSet<Integer> newSet = new HashSet<>(set);
                newSet.add(tree[cur][0]);
                if(infos[tree[cur][0]] == 0){
                    search(sheepCount + 1, wolfCount, newSet);
                } else {
                    search(sheepCount, wolfCount + 1, newSet);
                }
            }

            if(tree[cur][1] != -1 && !set.contains(tree[cur][1])){
                HashSet<Integer> newSet = new HashSet<>(set);
                newSet.add(tree[cur][1]);
                if(infos[tree[cur][1]] == 0){
                    search(sheepCount + 1, wolfCount, newSet);
                } else {
                    search(sheepCount, wolfCount + 1, newSet);
                }
            }
        }
    }
}
