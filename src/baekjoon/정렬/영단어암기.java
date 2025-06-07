package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 영단어암기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            if (word.length() < m) continue;
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }

        List<String> wordList = new ArrayList<>(freqMap.keySet());

        wordList.sort((w1, w2) -> {
            int freq1 = freqMap.get(w1);
            int freq2 = freqMap.get(w2);

            if (freq1 != freq2) {
                return freq2 - freq1;
            } else if (w1.length() != w2.length()) {
                return w2.length() - w1.length();
            } else {
                return w1.compareTo(w2);
            }
        });

        StringBuilder sb = new StringBuilder();
        for (String word : wordList) {
            sb.append(word).append("\n");
        }

        System.out.println(sb);
    }
}
