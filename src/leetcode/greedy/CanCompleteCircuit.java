package leetcode.greedy;

import java.util.PriorityQueue;

public class CanCompleteCircuit {

    public static void main(String[] args) {
        int[] gas = {2,3,4};
        int[] cost = {3,4,3};
//        int[] gas = {1,2,3,4,5};
//        int[] cost = {3,4,5,1,2};

        System.out.println(canCompleteCircuit(gas, cost));
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;

        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
        }

        if (totalGas < totalCost) return -1;

        int start = 0;
        int currentGas = 0;

        for (int i = 0; i < gas.length; i++) {
            currentGas += gas[i] - cost[i];

            if (currentGas < 0) {
                start = i;
                currentGas = 0;
            }
        }

        return start;
    }
}
