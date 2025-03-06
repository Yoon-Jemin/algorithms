package programmers.level_2;

import java.util.*;

public class 숫자카드나누기 {

    public static void main(String[] args) {
        int[] arrayA = {10, 20};
        int[] arrayB = {5, 17};
        System.out.println(solution(arrayA, arrayB));
    }

    public static int solution(int[] arrayA, int[] arrayB) {
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        return Math.max(card(arrayA, arrayB), card(arrayB, arrayA));
    }

    private static int card(int[] arrayA, int[] arrayB) {
        Set<Integer> candinates = new HashSet<>();
        int smallest = arrayA[0];
        for (int i = 1; i < smallest / 2 + 1; i++) {
            if (smallest % i == 0) {
                candinates.add(i);
                candinates.add(smallest / i);
            }
        }

        Set<Integer> remove = new HashSet<>();
        for (int i = 1; i < arrayA.length; i++) {
            int numA = arrayA[i];
            for (int candinate : candinates) {
                if (numA % candinate == 0) continue;
                remove.add(candinate);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int candinate : candinates) {
            if (remove.contains(candinate)) continue;
            result.add(candinate);
        }

        Collections.sort(result, Collections.reverseOrder());

        for (int i = 0; i < result.size(); i++) {
            boolean flag = true;
            for (int numB : arrayB) {
                if (numB % result.get(i) == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) return result.get(i);
        }

        return 0;
    }


}
