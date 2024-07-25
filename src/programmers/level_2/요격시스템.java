package programmers.level_2;

import java.util.*;

public class 요격시스템 {

    public static void main(String[] args) {

//        int[][] targets = {{1,3},{2,4},{3,5},{4,6}};
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
//        int[][] targets = {{4,5},{4,8},{11,14},{9,11},{5,12},{3,7},{1,4}};

        System.out.println(solution(targets));

    }

    public static int solution(int[][] targets) {

        int answer = 0;
        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]){
                    return Integer.compare(o1[0], o2[0]);
                }else {
                    return Integer.compare(o1[1], o2[1]);
                }
            }
        });

        Queue<int[]> targetQueue = new LinkedList<>();
        for(int[] target : targets) targetQueue.add(target);

        int[] nowTarget = targetQueue.poll();
        int limit = nowTarget[1];
        double shot = nowTarget[0] + 0.5;
        int[] nextTarget = targetQueue.peek();
        answer++;

        while (!targetQueue.isEmpty()){
            if(limit < shot){
                answer++;
                nowTarget = targetQueue.poll();
                if(targetQueue.isEmpty()) break;
                nextTarget = targetQueue.peek();
                limit = nowTarget[1];   // 12
            }
            if (nextTarget[0] < shot) {
                while (nextTarget[0] < shot){
                    if(nextTarget[1] < limit){
                        limit = nextTarget[1];
                    }
                    targetQueue.poll();
                    if(targetQueue.isEmpty()) break;
                    nextTarget = targetQueue.peek();
                }
            }
            shot++;
        }


        return answer;
    }
}
