package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 리모컨 {

    static int N;
    static int now = 100;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 리모컨에는 버튼이 0부터 9까지 숫자, +와 -
        // +를 누르면 현재 보고있는 채널에서 +1된 채널 (채널은 무한대 만큼 있다)
        // -를 누르면 -1된 채널로 이동 (채널 0에서 -를 누른 경우에는 채널이 변하지 않음)

        N = Integer.parseInt(st.nextToken());   // 수빈이가 이동하려는 채널

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());  // 고장난 버튼의 개수

        st = new StringTokenizer(br.readLine());
        Set<Integer> broken = new HashSet<>();
        for (int i = 0; i < m; i++) {
            broken.add(Integer.parseInt(st.nextToken()));
        }

        if (now == N) {
            System.out.println(0);
            return;
        }

        answer = Math.abs(N - now);
        DFS("", broken);
        System.out.println(answer);
    }

    private static void DFS(String letter, Set<Integer> broken) {
        String target = String.valueOf(N);
        if (letter.length() == target.length()) {    // 리프 노드
            calculate(Integer.valueOf(letter));
            return;
        }

        int index = letter.length();
        int best = target.charAt(index) - '0';

        int next1 = best - 1;   // best 보다 작은 값
        int next2 = best + 1;   // best 보다 큰 값

        while (true) {
            if (next1 == -1) next1 = 9;
            if (!broken.contains(next1)) {
                DFS(letter + next1, broken);
                break;
            }
            next1--;
        }

        while (true) {
            if (next2 == 10) next2 = 0;
            if (!broken.contains(next2)) {
                DFS(letter + next2, broken);
                break;
            }
            next2++;
        }

        if (!broken.contains(best)) {
            DFS(letter + best, broken);
        }
    }

    private static void calculate(int candidate) {
        int count = 0;
        count += Math.abs(candidate - N);
        count += String.valueOf(candidate).length();

        answer = Math.min(answer, count);
    }
}
