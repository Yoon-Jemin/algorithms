package programmers.고득점Kit.힙;
import java.util.*;

public class ex2_디스크컨트롤러 {
    public int solution(int[][] jobs) {
        int answer = 0;
        int currentTime = 0;

        int size = jobs.length;

        PriorityQueue<int[]> durationPriorityQueue = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]);   // 소요시간 우선순위 큐
        PriorityQueue<int[]> startPriorityQueue = new PriorityQueue<>((o1,o2) -> o1[0] - o2[0]); // 시작 시간 우선순위 큐

        startPriorityQueue.addAll(Arrays.asList(jobs));

        while (!startPriorityQueue.isEmpty() || !durationPriorityQueue.isEmpty()){
            while (!startPriorityQueue.isEmpty() && currentTime >= startPriorityQueue.peek()[0]){
                durationPriorityQueue.add(startPriorityQueue.poll());
            }
//            while (!durationPriorityQueue.isEmpty()){
//                int[] newJob = durationPriorityQueue.poll();
//                currentTime += newJob[1];
//                answer += (currentTime - newJob[0]);
//            }

            if(durationPriorityQueue.isEmpty()){
                currentTime = startPriorityQueue.peek()[0];
            }else {
                int[] newJob = durationPriorityQueue.poll();
                answer += currentTime + newJob[1] - newJob[0];
                currentTime += newJob[1];
            }
        }

        return answer/size;
    }
}
