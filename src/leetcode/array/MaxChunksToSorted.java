package leetcode.array;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MaxChunksToSorted {

    public static void main(String[] args) {
//        int[] arr = {4, 3, 2, 1, 0};
        int[] arr = {1, 0, 2, 3, 4};
//        int[] arr = {2, 0 ,1};
//        int[] arr = {2, 0 ,1, 3};
        System.out.println(maxChunksToSorted(arr));
    }

    public static int maxChunksToSorted(int[] arr) {
        int answer = 1;
        int smallest = 0;
        int largest = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) continue;

            largest = Math.max(largest, arr[i]);
            list.add(arr[i]);
            if (list.size() == largest - smallest + 1) {
                answer++;
                smallest = i + 1;
                largest = smallest;
                list.clear();
            }
        }

        return answer;
    }
}
