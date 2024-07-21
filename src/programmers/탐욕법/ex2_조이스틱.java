package programmers.탐욕법;

import java.util.HashMap;
import java.util.Map;

public class ex2_조이스틱 {
    public static void main(String[] args) {

        System.out.println(solution("JEROEN"));
    }

    public static int solution(String name) {
        int size = name.length();

        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Map<Character, Integer> alphaMap = new HashMap<>();

        for(int i = 1; i <= alphabets.length(); i++){
            alphaMap.put(alphabets.charAt(i-1), i);
        }

        int count = 0;
        for(int i = 0; i < size; i++){
            Character next = name.charAt(i);   // 단어
            int nextLocation = alphaMap.get(next);  // 단어 위치

            int fromStart = nextLocation - 1;
            int fromEnd = alphabets.length() - nextLocation + 1;

            count += Math.min(fromStart, fromEnd);
        }

        int move = size -1;
        for (int i = 0; i < size; i++) {
            // next = i 이후 첫 번째 'A'가 아닌 문자 위치
            int next = i + 1;
            while (next < size && name.charAt(next) == 'A') {
                next++;
            }

            // i + size - next: i에서 시작하여 next 위치로 이동하는 경로
            // Math.min(i, size - next): 앞으로 갔다가 되돌아오는 경우(i), 처음부터 뒤로 가는 경우(size-next)
            move = Math.min(move, (i + size - next) + Math.min(i, size - next));
        }

        return count + move;

    }
}
