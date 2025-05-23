package baekjoon.백트래킹;

import java.util.*;

public class 암호만들기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   // 암호의 길이
        int c = sc.nextInt();   // 문자의 종류 -> 최소 1개의 모음, 최소 2개의 자음

        Set<String> vowels = new HashSet<String>();
        vowels.add("a");
        vowels.add("e");
        vowels.add("i");
        vowels.add("o");
        vowels.add("u");

        List<String> alphabets = new ArrayList<String>();
        for (int i = 0; i < c; i++) {
            alphabets.add(sc.next());
        }

        Collections.sort(alphabets);

        backTrack(0, n, new ArrayList<>(), vowels, alphabets);
    }

    private static void backTrack(int start, int n, List<String> password, Set<String> vowels, List<String> alphabets) {
        if (password.size() == n) {
            if (isValid(password, vowels)) {
                for (int i = 0; i < n; i++) {
                    System.out.print(password.get(i));
                }
                System.out.println();
            }
            return;
        }

        for (int i = start; i < alphabets.size(); i++) {
            password.add(alphabets.get(i));
            backTrack(i + 1, n, password, vowels, alphabets);
            password.remove(password.size() - 1);
        }

    }

    private static boolean isValid(List<String> password, Set<String> vowels) {
        int countConstants = 0;
        int countVowels = 0;

        for (String p : password) {
            if (vowels.contains(p)) countVowels++;
            else countConstants++;
        }

        return countConstants >= 2 && countVowels >= 1;
    }
}
