package baekjoon.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 임스와함께하는미니게임 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int limit = 0;
        String game = st.nextToken();
        if (game.equals("Y")) limit = 2;
        else if (game.equals("F")) limit = 3;
        else if (game.equals("O")) limit = 4;

        Set<String> set = new HashSet<>();
        int answer = 0;
        int group = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String person = st.nextToken();

            if (set.contains(person)) continue;

            set.add(person);
            group++;

            if (group == limit - 1) {
                answer++;
                group = 0;
            }
        }

        System.out.println(answer);
    }
}
