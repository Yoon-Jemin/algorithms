package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 가르침 {

    static int N, K;
    static int answer = 0;
    static Set<Integer> learned;
    static List<String> words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // K개의 글자를 가르칠 시간 밖에 없다
        // 학생들은 그 K개의 글자로만 이루어진 단어만을 읽을 수 있다
        // 어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어의 개수가 최대가 되는지
        // 모든 단어는 "anta"로 시작되고, "tica"로 끝난다

        N = Integer.parseInt(st.nextToken());   // 단어의 개수
        K = Integer.parseInt(st.nextToken());   // 가르칠 수 있는 글자의 개수

        if (K < 5) {    // 읽을 수 있는 단어가 없음
            System.out.println(0);
            return;
        }

        words = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            words.add(br.readLine());
        }

        learned = new HashSet<>();
        learned.add('a' - 'a');
        learned.add('n' - 'a');
        learned.add('t' - 'a');
        learned.add('i' - 'a');
        learned.add('c' - 'a');

        backTrack(0);
        System.out.println(answer);
    }

    private static void backTrack(int start) {
        if (learned.size() == K) {
            check();
            return;
        }

        for (int i = start; i < 26; i++) {
            int letter = i;

            if (learned.contains(letter)) continue;

            learned.add(letter);
            backTrack(i + 1);
            learned.remove(letter);
        }
    }

    private static void check() {
        int count = 0;

        for (String word : words) {
            boolean canRead = true;
            for (int i = 0; i < word.length(); i++) {
                if (!learned.contains(word.charAt(i) - 'a')) {
                    canRead = false;
                    break;
                }
            }

            if (canRead) count++;
        }

        answer = Math.max(answer, count);
    }
}
