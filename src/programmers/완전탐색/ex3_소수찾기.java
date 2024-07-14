package programmers.완전탐색;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ex3_소수찾기 {
    public static void main(String[] args) {
        String numbers = "7";
//        System.out.println(numbers.substring(1));
        System.out.println(solution(numbers));
    }
    public static int solution(String numbers) {
        int answer = 0;

        Set<String> strCombinations = new HashSet<>();
        Set<Integer> intCombinations = new HashSet<>();

        generateCombinations(numbers, "", strCombinations);

        for(String combination : strCombinations){
            int number = Integer.parseInt(combination);
            if(isPrime(number) && !intCombinations.contains(number)){
                intCombinations.add(number);
                answer++;
            }
        }

        return answer;
    }

    private static void generateCombinations(
            String numbers,
            String current,
            Set<String> combinations
    ) {
       if(!current.isEmpty()){
           combinations.add(current);
       }

       for(int i = 0; i < numbers.length(); i++){
           generateCombinations(
                   numbers.substring(0,i) + numbers.substring(i+1),
                   current + numbers.charAt(i),
                   combinations
           );
       }
    }

    public static boolean isPrime(Integer number){
        if(number == 1) return false;
        if(number == 2) return true;
        if(number % 2 == 0){
            return false;
        }
        else {
            for(int i = 3; i <= Math.sqrt(number); i++){
                if(number % i == 0) return false;
            }
        }
        return true;
    }
}
