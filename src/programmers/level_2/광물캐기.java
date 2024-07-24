package programmers.level_2;

import java.util.*;

public class 광물캐기 {

    public static void main(String[] args) {
        int[] picks = {2, 1, 1};
        String[] minerals = {
                "diamond", "iron", "stone", "diamond", "iron",
                "diamond", "diamond", "diamond", "diamond", "diamond",
                "stone", "iron", "stone", "iron", "stone"
        };

        System.out.println(solution(picks, minerals));

    }

    public static int solution(int[] picks, String[] minerals) {

        int answer = 0;

        Deque<String> pickDeque = new LinkedList<>();
        for(int i = 0; i < picks[0]; i++) pickDeque.add("diamond");
        for(int i = 0; i < picks[1]; i++) pickDeque.add("iron");
        for(int i = 0; i < picks[2]; i++) pickDeque.add("stone");

        Deque<int []> workList = new LinkedList<>();
        int[] work = new int[3];
        int count = 0;
        for(String mineral : minerals){
            if(mineral.equals("diamond")){
                work[0] += 1; work[1] += 5; work[2] += 25;
            }
            if(mineral.equals("iron")){
                work[0] += 1; work[1] += 1; work[2] += 5;
            }
            if(mineral.equals("stone")){
                work[0] += 1; work[1] += 1; work[2] += 1;
            }
            count++;
            if(count % 5 == 0){
                workList.add(work);
                work = new int[3];
            }
        }

        if(Arrays.stream(work).sum() != 0){
            workList.add(work);
        }

        if(workList.size() > pickDeque.size()){
            while (workList.size() != pickDeque.size()) workList.pollLast();
        } else if (workList.size() < pickDeque.size()) {
            while (workList.size() != pickDeque.size()) pickDeque.pollLast();
        }

        while (!pickDeque.isEmpty()){
            String pick = pickDeque.pollLast();

            if(pick.equals("stone")){
                int min = 626;
                int[] minObject = new int[3];
                for(int[] w : workList){
                    if(w[2] < min){
                        minObject = w;
                        min = w[2];
                    }
                }
                answer += minObject[2];
                workList.remove(minObject);
            } else if (pick.equals("iron")){
                int min = 26;
                int[] minObject = new int[3];
                for(int[] w : workList){
                    if(w[1] < min){
                        minObject = w;
                        min = w[1];
                    }
                }
                answer += minObject[1];
                workList.remove(minObject);
            } else if (pick.equals("diamond")) {
                int[] newWork = workList.poll();
                answer += newWork[0];
            }

        }

        return answer;
    }


}
