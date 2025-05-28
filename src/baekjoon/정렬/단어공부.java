package baekjoon.정렬;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 단어공부 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        int[] frequency = new int[26];
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b) -> b[1] - a[1]);   // 단어 : 단어 빈도수

        for (int i = 0; i < word.length(); i++) {
            String s = word.substring(i, i + 1);
            char c = s.toLowerCase().charAt(0);
            frequency[c - 'a']++;
            pq.offer(new int[]{c - 'a', frequency[c - 'a']});
        }

        int[] first = pq.poll();
        String answer = alphabet.substring(first[0], first[0] + 1).toUpperCase();

        if (!pq.isEmpty()) {
            int[] second = pq.poll();
            if (first[1] == second[1]) {
                System.out.println("?");
                return;
            }
        }

        System.out.println(answer);
    }
}
