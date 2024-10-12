package programmers.level_2;

import java.util.ArrayList;

public class 삼각달팽이 {

    public static void main(String[] args) {
        int[] numbers = solution(6);
        for(int number : numbers) System.out.println(number);
    }

    //         1
    //       2  12
    //     3  13  11
    //   4  14  15  10
    // 5   6   7   8   9

    public static int[] solution(int n) {

        ArrayList<Integer> result = new ArrayList<>();

        int max = 0;
        ArrayList<int[]> numbers = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            numbers.add(new int[i]);
            max += i;
        }

        int level = 0;
        int location = 0;
        String direction = "down";
        for(int i = 1; i <= max; i++) {
            numbers.get(level)[location] = i;
            if(direction.equals("down")) {
                if(level < numbers.size() - 1 && numbers.get(level+1)[location] == 0) {
                    level++;
                    direction = "down";
                } else {
                    location++;
                    direction = "right";
                }
            } else if (direction.equals("right")) {
                if(location < numbers.get(level).length -1) {
                    location++;
                    direction = "right";
                } else {
                    location--;
                    level--;
                    direction = "up";
                }
            } else if (direction.equals("up")) {
                if(numbers.get(level-1)[location-1] == 0) {
                    location--;
                    level--;
                    direction = "up";
                } else {
                    level++;
                    direction = "down";
                }
            }
        }

        for(int[] number : numbers) {
            for(int num : number) {
                result.add(num);
            }
        }

        int[] resultList = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            resultList[i] = result.get(i);
        }
        return resultList;
    }
}
