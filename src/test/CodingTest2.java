package test;

import java.util.HashMap;
import java.util.Map;

public class CodingTest2 {
    public static void main(String[] args){

        int[][] arr = {{1,3,2,0}, {2,0,1,3}, {1,2,0,3}};
        int[] result = solution(arr);

        for(int num : result) System.out.println(num);
    }

    public static int[] solution(int[][] arr) {
        int size = arr.length;  // 데이터의 수
        int length = arr[0].length; // 행의 수
        int[] answer = new int[length];

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();  // [키: 행, 값; [키:숫자 ,값: 숫자의 위치]]

        // 데이터 초기화
        for(int i = 0; i < size; i++){
            Map<Integer, Integer> insideMap = new HashMap<>();
            for(int j = 0; j < length; j++){
                insideMap.put(arr[i][j],j);
            }
            map.put(i, insideMap);
        }

        // 로직
        for(int i = 0; i < length; i++){
            int distance = 0;

            int target = i;     // 대상 숫자
            int location = map.get(0).get(target);  // 첫 번째 행에서 대상 숫자 위치

            for(int j = 1; j <= size - 1; j++){
                int newlocation = map.get(j).get(target);
                int result = Math.abs(newlocation - location);
                distance += result;
                location = newlocation;
            }

            answer[i] = distance;

        }

        return answer;
    }
}
