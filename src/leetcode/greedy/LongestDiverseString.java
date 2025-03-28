package leetcode.greedy;

import java.util.*;

public class LongestDiverseString {

    public static void main(String[] args) {
        System.out.println(longestDiverseString(7, 1, 0));  // ccaccbcc
    }

    public static String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Letter> pq = new PriorityQueue<>((x,y) -> y.frequency - x.frequency);
        if (a != 0) pq.add(new Letter("a", a));
        if (b != 0) pq.add(new Letter("b", b));
        if (c != 0) pq.add(new Letter("c", c));

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Letter first = pq.poll();

            if (sb.toString().length() >= 2
            && sb.charAt(sb.length() - 1) == sb.charAt(sb.length() - 2)
            && sb.toString().substring(sb.toString().length() - 1).equals(first.letter)) { // 3번 연속
                if (pq.isEmpty()) break;
                Letter second = pq.poll();
                sb.append(second.letter);
                second.frequency--;
                if (second.frequency > 0) pq.add(second);
                pq.add(first);
            } else {
                sb.append(first.letter);
                first.frequency--;
                if (first.frequency > 0) pq.add(first);
            }
        }

        return sb.toString();
    }

    public static class Letter {
        String letter;
        int frequency;

        public Letter(String letter, int frequency) {
            this.letter = letter;
            this.frequency = frequency;
        }
    }
}
