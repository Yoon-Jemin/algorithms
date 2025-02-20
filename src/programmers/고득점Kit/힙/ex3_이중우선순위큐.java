package programmers.고득점Kit.힙;
import java.util.*;

public class ex3_이중우선순위큐 {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        Map<Integer, Integer> map = new HashMap<>();

        for(String s : operations){
            if(s.charAt(0) == 'D'){
                // 삭제 연산
                String[] parts = s.split(" ");
                int number = Integer.parseInt(parts[1]);
                if(number == -1){   // 최소 값 지우기
                    while(!minPQ.isEmpty() && map.get(minPQ.peek()) != 1){  // map을 통해 지워진 값인지 확인
                        minPQ.poll();
                    }
                    if(!minPQ.isEmpty()){
                        map.put(minPQ.peek(), map.get(minPQ.peek()) -1);
                        minPQ.poll();
                    }
                }
                if(number == 1){    // 최대 값 지우기
                    while(!maxPQ.isEmpty() && map.get(maxPQ.peek()) != 1){  // map을 통해 지워진 값인지 확인
                        maxPQ.poll();
                    }
                    if(!maxPQ.isEmpty()){
                        map.put(maxPQ.peek(), map.get(maxPQ.peek()) -1);
                        maxPQ.poll();
                    }
                }
            } else if (s.charAt(0) == 'I') {
                // 삽입 연산
                String[] parts = s.split(" ");
                int number = Integer.parseInt(parts[1]);
                map.put(number, map.getOrDefault(number, 0) + 1);   // map에 값 업데이트
                maxPQ.add(number);
                minPQ.add(number);
            }
        }

        while(!maxPQ.isEmpty()){
            if(map.get(maxPQ.peek()) != 0){
                answer[0] = maxPQ.peek();
                break;
            } else {
                maxPQ.poll();
            }
        }

        while(!minPQ.isEmpty()){
            if(map.get(minPQ.peek()) != 0){
                answer[1] = minPQ.peek();
                break;
            } else {
                minPQ.poll();
            }
        }

        return answer;
    }
}
