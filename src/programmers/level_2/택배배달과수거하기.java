package programmers.level_2;

import java.util.Arrays;

public class 택배배달과수거하기 {

    public static void main(String[] args) {

        int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
        int[] pickups = {0, 2, 0, 1, 0, 2, 0};

        System.out.println(solution(2, 7, deliveries, pickups));
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int deliveryIndex = deliveries.length - 1;
        int pickupIndex = pickups.length - 1;

        int deliverySum = Arrays.stream(deliveries).sum();
        int pickupSum = Arrays.stream(pickups).sum();

        int load = 0;

        while(deliverySum + pickupSum != 0) {

            if(deliverySum >= cap) load = cap;
            else load = deliverySum;

            if(deliveries[deliveryIndex] == 0 && pickups[pickupIndex] == 0) {
                deliveryIndex--;
                pickupIndex--;
                continue;
            }

            if(deliveryIndex > pickupIndex) answer += (deliveryIndex + 1) * 2L;
            else answer += (pickupIndex + 1) * 2L;

            // 배달
            while(load > 0){
                if(deliveryIndex < 0) break;
                if(deliveries[deliveryIndex] == 0) {
                    deliveryIndex--;
                    continue;
                }
                if(deliveries[deliveryIndex] <= load){
                    load -= deliveries[deliveryIndex];
                    deliverySum -= deliveries[deliveryIndex];
                    deliveries[deliveryIndex] = 0;
                    deliveryIndex--;
                } else if (load < deliveries[deliveryIndex]) {
                    deliverySum -= load;
                    deliveries[deliveryIndex] -= load;
                    load = 0;
                }
            }

            // 수거
            while(load < cap){
                if(pickupIndex < 0) break;
                if(pickups[pickupIndex] == 0) {
                    pickupIndex--;
                    continue;
                }
                if(pickups[pickupIndex] + load <= cap){
                    load += pickups[pickupIndex];
                    pickupSum -= pickups[pickupIndex];
                    pickups[pickupIndex] = 0;
                    pickupIndex--;
                } else if(pickups[pickupIndex] + load > pickups[pickupIndex]){
                    pickupSum -= pickups[pickupIndex] - (cap - load);
                    pickups[pickupIndex] -= cap - load;
                    load = cap;
                }
            }

        }

        return answer;
    }
}
