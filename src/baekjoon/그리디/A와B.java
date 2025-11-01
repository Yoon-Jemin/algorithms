package baekjoon.그리디;

import java.io.IOException;
import java.util.Scanner;

public class A와B {

    // 문자열의 뒤에 A를 추가한다.
    // 문자열을 뒤집고 뒤에 B를 추가한다.
    // B -> ABBA
    // AB -> ABB
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        String word1 = sc.next();
        String word2 = sc.next();

        while (word2.length() != word1.length()) {
            if (word2.charAt(word2.length() - 1) == 'A') {
                word2 = word2.substring(0, word2.length() - 1);
            } else if (word2.charAt(word2.length() - 1) == 'B') {
                word2 = eraseAndFlip(word2);
            }
        }

        if (word1.equals(word2)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    public static String eraseAndFlip(String word) {
        word = word.substring(0, word.length() - 1);

        String newWord = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            newWord += word.charAt(i);
        }

        return newWord;
    }
}
