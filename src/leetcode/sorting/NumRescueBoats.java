package leetcode.sorting;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class NumRescueBoats {

    public static void main(String[] args) {
        int[] people = {2,49,10,7,11,41,47,2,22,6,13,12,33,18,10,26,2,6,50,10};
        int limit = 50;
//        int[] people = {1,1,1,1,1,1,1,1,1,1,1,4,4};
//        int limit = 4;
        System.out.println(numRescueBoats(people, limit));
    }

    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        Deque<Integer> deque = new ArrayDeque<>();
        for (int p : people) {
            deque.addLast(p);
        }

        int count = 0;
        while (!deque.isEmpty()) {
            count++;
            int weight = deque.removeLast();

            if (!deque.isEmpty()) {
                if (weight + deque.peekFirst() <= limit) weight += deque.removeFirst();
            }
        }

        return count;
    }
}
