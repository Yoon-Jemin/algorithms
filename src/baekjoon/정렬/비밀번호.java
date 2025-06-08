package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 비밀번호 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String password;

        while (!(password = br.readLine()).equals("end")) {
            if (isAcceptable(password)) {
                System.out.println("<" + password + "> is acceptable.");
            } else {
                System.out.println("<" + password + "> is not acceptable.");
            }
        }
    }

    private static boolean isAcceptable(String password) {
        Set<Character> vowels = new HashSet<Character>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        boolean hasVowel = false;


        for (int i = 0; i < password.length(); i++) {
            char nowChar = password.charAt(i);

            if (vowels.contains(nowChar)) {
                hasVowel = true;
            }

            if (i <= password.length() - 3) {
                boolean isVowel1 = vowels.contains(password.charAt(i));
                boolean isVowel2 = vowels.contains(password.charAt(i + 1));
                boolean isVowel3 = vowels.contains(password.charAt(i + 2));

                if ((isVowel1 && isVowel2 && isVowel3) ||
                        (!isVowel1 && !isVowel2 && !isVowel3)) {
                    return false;
                }
            }

            if (i > 0) {
                char prevChar = password.charAt(i - 1);
                if (prevChar == nowChar) {
                    if (nowChar != 'e' && nowChar != 'o') return false;
                }
            }
        }

        return hasVowel;
    }
}
