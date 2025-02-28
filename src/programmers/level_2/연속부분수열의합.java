package programmers.level_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class 연속부분수열의합 {

    public static void main(String[] args) {
        int[] elements = {7, 9, 1, 1, 4};
        System.out.println(solution(elements));
    }

    public static int solution(int[] elements) {
        int n = elements.length;
        HashSet<Integer> set = new HashSet<>();

        int total = 0;
        for (int i = 0; i < n; i++) {
            total += elements[i];
        }
        set.add(total);

        for (int i = 0; i < n; i++) {
            for (int max = 1; max < n; max++) {
                int sum = 0;
                int count = 0;
                int start = i;
                while (count < max) {
                    sum += elements[start];
                    count++;
                    if (start == n - 1) start = 0;
                    else start++;
                }
                set.add(sum);
            }
        }

        return set.size();
    }
}
