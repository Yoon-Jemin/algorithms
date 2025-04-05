package leetcode.greedy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class maximumEvenSplit {

    public static void main(String[] args) {
        System.out.println(maximumEvenSplit(12L));
    }

    public static List<Long> maximumEvenSplit(long finalSum) {
        if (finalSum % 2 != 0) return new ArrayList<>();

        Set<Long> result = new HashSet<>();
        long sum = 0;
        long even = 2;

        while (sum < finalSum) {
            sum += even;
            result.add(even);
            even += 2;
        }

        if (sum > finalSum) {
            result.remove(sum - finalSum);
        }

        return new ArrayList<>(result);
    }
}
