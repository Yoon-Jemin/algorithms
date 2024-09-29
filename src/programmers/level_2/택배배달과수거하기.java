package programmers.level_2;

import java.util.Arrays;
import java.util.Stack;

public class 택배배달과수거하기 {

    public static void main(String[] args) {

        int[] deliveries = {1, 0, 2, 0, 1, 0, 2};
        int[] pickups = {0, 2, 0, 1, 0, 2, 0};

        System.out.println(solution(2, 7, deliveries, pickups));
    }

    public static long solution(int cap, int n, int[] deliveries, int[] pickups) {

        long answer = 0;

        Stack<int[]> deliveryStack = new Stack<>();
        Stack<int[]> pickupStack = new Stack<>();

        for(int i = 0; i < deliveries.length; i++) {
            if(deliveries[i] != 0) deliveryStack.push(new int[]{i+1, deliveries[i]});
            if(pickups[i] != 0) pickupStack.push(new int[]{i+1, pickups[i]});
        }

        int capDelivery = 0;
        int capPickup = 0;

        while(!deliveryStack.isEmpty() || !pickupStack.isEmpty()) {
            capDelivery = cap;
            capPickup = cap;

            if(!deliveryStack.isEmpty() && !pickupStack.isEmpty()) {
                answer += (deliveryStack.peek()[0] >= pickupStack.peek()[0] ? deliveryStack.peek()[0] : pickupStack.peek()[0]) * 2;
            } else if (!deliveryStack.isEmpty()) {      // 배달 재고만 남음
                answer += deliveryStack.peek()[0] *2;
                capPickup = 0;
            } else if (!pickupStack.isEmpty()) {    // 수거 재고만 남음
                answer += pickupStack.peek()[0] *2;
                capDelivery = 0;
            }

            while(capDelivery != 0 && !deliveryStack.isEmpty()) {
                int[] temp = deliveryStack.pop();
                if(temp[1] <= capDelivery) {
                    capDelivery -= temp[1];
                } else {
                    temp[1] -= capDelivery;
                    capDelivery = 0;
                    deliveryStack.push(temp);
                }
            }

            while(capPickup != 0 && !pickupStack.isEmpty()) {
                int[] temp = pickupStack.pop();
                if (temp[1] <= capPickup) {
                    capPickup -= temp[1];
                } else {
                    temp[1] -= capPickup;
                    capPickup = 0;
                    pickupStack.push(temp);
                }
            }
        }

        return answer;
    }
}
