package leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class PancakeSorting {

    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 1};
        System.out.println(pancakeSort(arr));
    }

    public static List<Integer> pancakeSort(int[] arr) {
        List<Integer> list = new ArrayList<>();

        int limit = arr.length - 1;
        while (limit > 0) {
            int max = -1;
            int maxIndex = -1;
            for (int i = 0; i <= limit; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                    maxIndex = i;
                }
            }
            if (maxIndex != limit) {    // flip 필요
                flip(maxIndex + 1, arr);
                list.add(maxIndex + 1);
                flip(limit + 1, arr);
                list.add(limit + 1);
            }
            limit--;
        }

        return list;
    }

    // index = 3
    // flip 전: arr[] = {3, 2, 4, 1}
    // flip 후: arr[] = {4, 2, 3, 1}
    private static void flip(int index, int[] arr) {
        int[] temp = new int[index];

        for (int i = 0; i < index; i++) {
            temp[i] = arr[index - 1 - i];
        }

        for (int i = 0; i < index; i++) {
            arr[i] = temp[i];
        }
    }
}
