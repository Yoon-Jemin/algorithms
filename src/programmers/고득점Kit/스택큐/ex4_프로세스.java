package programmers.고득점Kit.스택큐;
import java.util.*;
public class ex4_프로세스 {
    public int solution(int[] priorities, int location) {
        int answer = 1;

        int [] sortedPriorities = Arrays.stream(priorities)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        ArrayList<Integer> arrayList = new ArrayList<>();   // 위치

        Queue<int []> queue = new LinkedList<>();    // [우선순위값, 위치]

        for(int i = 0; i < priorities.length; i++){
            queue.add(new int[] {priorities[i], i});
        }

        int index = 0;
        while (!queue.isEmpty()){
            if(queue.peek()[0] == sortedPriorities[index]){
                int[] priority = queue.poll();
                arrayList.add(priority[1]);
                index++;
            } else {
                queue.add(queue.poll());
            }
        }

        for(int num : arrayList){
            if(num == location){
                break;
            }
            answer++;
        }

        return answer;
    }
}
