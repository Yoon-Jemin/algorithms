package leetcode.greedy;

import java.util.HashMap;
import java.util.Map;

public class MinSwapsCouples {

    public static void main(String[] args) {
        int[] row = {1,4,0,5,8,7,6,3,2,9};
        System.out.println(minSwapsCouples(row));
    }

    public static int minSwapsCouples(int[] row) {
        Map<Integer, Integer> couple = new HashMap<>();
        for (int i = 0; i < row.length; i += 2) {
            couple.put(i, i + 1);
            couple.put(i + 1, i);
        }

        Map<Integer, Integer> index = new HashMap<>();     // 번호, 위치
        for (int i = 0; i < row.length; i++) {
            index.put(row[i], i);
        }

        int swaps = 0;
        for (int i = 0; i < row.length; i += 2) {
            int firstPerson = row[i];
            int secondPerson = couple.get(firstPerson);

            if (secondPerson == row[i + 1]) continue;

            int temp = row[i + 1];
            row[index.get(secondPerson)] = temp;
            row[i + 1] = secondPerson;

            index.put(temp, index.get(secondPerson));
            index.put(secondPerson, i + 1);

            swaps++;
        }

        return swaps;
    }
}
