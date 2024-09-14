package programmers.level_2;

import java.util.HashMap;

public class 할인행사 {

    public static void main(String[] args) {
        String[] want = {"banana", "apple", "rice", "pork", "pot"};
        int[] number = {3, 2, 2, 2, 1};
        String[] discount = {"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice",
                "pot", "banana", "apple", "banana"};

        System.out.println(solution(want, number, discount));
    }

    public static int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        HashMap<String, int[]> map = new HashMap<>();   // {key: 음식, value: [충족되어야 하는 수량, 현재 수량]}

        for (int i = 0; i < want.length; i++) {
            map.put(want[i], new int[]{number[i], 0});
        }

        for(int i = 0; i < 10; i++){
            String item = discount[i];
            if(map.containsKey(item)){
                int amount = map.get(item)[1] + 1;
                map.put(item, new int[]{map.get(item)[0], amount});
            }
        }

        boolean isTrue = true;
        for(int[] list: map.values()){
            if(list[0] > list[1]){
                isTrue = false;
                break;
            }
        }

        if(isTrue) answer++;

        for(int i = 10; i < discount.length; i++){
            int addIdx = i;
            int removeIdx = i - 10;
            // 추가 로직
            String addItem = discount[addIdx];
            if(map.containsKey(addItem)){
                int amount = map.get(addItem)[1] + 1;
                map.put(addItem, new int[]{map.get(addItem)[0], amount});
            }
            // 삭제 로직
            String removeItem = discount[removeIdx];
            if(map.containsKey(removeItem)){
                int amount = map.get(removeItem)[1] - 1;
                map.put(removeItem, new int[]{map.get(removeItem)[0], amount});
            }

            isTrue = true;
            for(int[] list: map.values()){
                if(list[0] > list[1]){
                    isTrue = false;
                    break;
                }
            }

            if(isTrue) answer++;
        }

        return answer;
    }
}
