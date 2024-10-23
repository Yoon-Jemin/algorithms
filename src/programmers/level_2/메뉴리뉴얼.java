package programmers.level_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class 메뉴리뉴얼 {

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2,3,4};
        String[] results = solution(orders, course);
        for (String result : results) {
            System.out.println(result);
        }

    }

    private static StringBuilder sb = new StringBuilder();

    private static HashMap<String, Integer> hashMap = new HashMap<>();
    public static String[] solution(String[] orders, int[] course) {
        ArrayList<String> list = new ArrayList<>();

        for(int i = 0; i < orders.length; i++) {
            char[] charArr = orders[i].toCharArray();
            boolean[] visited = new boolean[charArr.length];

            for(int j = 0; j < course.length; j++) {
                if(charArr.length >= course[j]) {
                    combination(charArr, visited, 0, charArr.length, course[j]);
                }
            }
        }

        for(int i = 0; i < course.length; i++) {
            int max = 0;
            for(String key : hashMap.keySet()) {
                if(key.length() == course[i]) {
                    int num = hashMap.get(key);
                    if(num > max && num > 1) {
                        max = num;
                    }
                }
            }

            for(String key : hashMap.keySet()) {
                if(key.length() == course[i]) {
                    int num = hashMap.get(key);
                    if(num == max){
                        list.add(key);
                    }
                }
            }
        }

        Collections.sort(list);

        String[] answer = list.toArray(new String[list.size()]);

        return answer;
    }

    /**
     * 주어진 charArr 배열에서 r개의 원소를 선택하는 모든 경우의 수
     * charArr: 조합을 구할 대상이 되는 문자 배열
     * visited: 각 문자가 현재 조합에 포함되는지 여부를 기록
     * start: 조합을 시작할 인덱스 (값이 점점 커지면서 중복된 조합을 방지)
     * n: charArr 배열의 길이입니다. 즉, 선택 가능한 원소의 총 개수
     * r: 현재 단계에서 더 선택해야 할 원소의 개수
    * */
    private static void combination(char[] charArr, boolean[] visited, int start, int n, int r) {
        if(r == 0){
            put(charArr, visited, n);
            return;
        }

        for(int i = start; i < n; i++){
            visited[i] = true;
            combination(charArr, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    private static void put(char[] charArr, boolean[] visited, int n) {
        sb.setLength(0);
        for(int i = 0; i < n; i++){
            if(visited[i]){
                sb.append(charArr[i]);
            }
        }

        char[] set = sb.toString().toCharArray();
        Arrays.sort(set);

        String str = new String(set);

        if(!hashMap.containsKey(str)){
            hashMap.put(str, 1);
        } else {
            int num = hashMap.get(str);
            hashMap.put(str, num + 1);
        }
    }
}
