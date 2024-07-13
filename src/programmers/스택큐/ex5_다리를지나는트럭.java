package programmers.스택큐;
import java.util.*;

public class ex5_다리를지나는트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        int bridgeWeight = 0;
        int truckOnBridge = 0;
        int nextBridgeWeight = 0;
        Queue<Integer> bridgeQueue = new LinkedList<>();    // 다리
        Queue<Integer> truckQueue = new LinkedList<>();     // 대기 트럭

        for(int i = 0; i < bridge_length; i++) bridgeQueue.add(0);
        for(int truck: truck_weights) truckQueue.add(truck);

        while (!truckQueue.isEmpty()){
            answer++;

            nextBridgeWeight = bridgeWeight + truckQueue.peek() - bridgeQueue.peek();

            if(nextBridgeWeight <= weight){  // 다음 트럭 수용 가능
                bridgeWeight = nextBridgeWeight;
                bridgeQueue.add(truckQueue.poll());
                truckOnBridge++;
                if(bridgeQueue.poll() != 0) truckOnBridge--;
            } else {    // 다음 트럭 수용 불가능
                if(truckOnBridge == bridge_length){
                    bridgeWeight -= bridgeQueue.poll();
                    truckOnBridge--;
                    bridgeQueue.add(0);
                } else {
                    bridgeWeight -= bridgeQueue.peek();
                    bridgeQueue.add(0);
                    if(bridgeQueue.poll() != 0) truckOnBridge--;
                }
            }
        }
        answer += bridge_length;

        return answer;
    }
}
