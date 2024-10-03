package programmers.level_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 튜플 {

    public static int[] solution(String s) {

        String input = s;
        input = input.substring(1, input.length() - 1);
        String[] groups = input.split("\\},\\{");

        ArrayList<int[]> lists = new ArrayList<>();

        for (String group : groups) {
            group = group.replaceAll("[{}]", "");  // 중괄호 제거
            String[] numbers = group.split(",");   // 숫자들을 ','로 분리

            int[] intArray = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                intArray[i] = Integer.parseInt(numbers[i]);
            }

            insertSortedByLength(lists, intArray);
        }


        Set<Integer> set = new HashSet<>();

        int[] answer = new int[lists.size()];

        int index = 0;
        for(int[] list : lists) {
            for(int num : list) {
                if(!set.contains(num)) {
                    set.add(num);
                    answer[index] = num;
                    index++;
                    break;
                }
            }
        }

        return answer;
    }

    private static void insertSortedByLength(ArrayList<int[]> lists, int[] intArray) {
        int index = 0;

        // 배열의 길이에 맞는 위치를 찾음
        while (index < lists.size() && lists.get(index).length <= intArray.length) {
            index++;
        }

        // 해당 위치에 배열 삽입
        lists.add(index, intArray);
    }
}
