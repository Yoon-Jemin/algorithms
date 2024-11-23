package leetcode.hashtable;

import java.util.HashMap;
import java.util.Map;

public class IntToRoman {

    public static void main(String[] args) {
        System.out.println(intToRoman(3749));
    }

    public static String intToRoman(int num) {
        StringBuilder answer = new StringBuilder();

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");

        int divisor = 1000;
        while (divisor > 0) {
            int firstPosition = num / divisor;  // 몫

            String divisor_1, divisor_5, divisor_10;
            if(divisor == 1000){
                divisor_1 = map.get(divisor);
                divisor_5 = "";
                divisor_10 = "";
            } else{
                divisor_1 = map.get(divisor);
                divisor_5 = map.get(divisor * 5);
                divisor_10 = map.get(divisor * 10);
            }

            if(firstPosition != 0){
                if(firstPosition == 9){
                    answer.append(divisor_1);
                    answer.append(divisor_10);
                } else if(firstPosition == 8) {
                    answer.append(divisor_5);
                    answer.append(divisor_1);
                    answer.append(divisor_1);
                    answer.append(divisor_1);
                } else if(firstPosition == 7) {
                    answer.append(divisor_5);
                    answer.append(divisor_1);
                    answer.append(divisor_1);
                } else if(firstPosition == 6) {
                    answer.append(divisor_5);
                    answer.append(divisor_1);
                } else if(firstPosition == 5) {
                    answer.append(divisor_5);
                } else if(firstPosition == 4) {
                    answer.append(divisor_1);
                    answer.append(divisor_5);
                } else if(firstPosition == 3) {
                    answer.append(divisor_1);
                    answer.append(divisor_1);
                    answer.append(divisor_1);
                } else if(firstPosition == 2) {
                    answer.append(divisor_1);
                    answer.append(divisor_1);
                } else if(firstPosition == 1) {
                    answer.append(divisor_1);
                }
            }

            num %= divisor; // 나머지로 숫자 업데이트
            divisor /= 10;
        }

        return answer.toString();
    }
}
