package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 체인 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> chains = new ArrayList<>();
        for (int i = 0; i < n; i++) chains.add(Integer.parseInt(st.nextToken()));

        Collections.sort(chains);
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) deque.add(chains.get(i));

        int connectedChains = deque.pollLast();
        int connectRings = deque.pollFirst();
        int answer = 0;
        while (!deque.isEmpty()) {
            if (connectRings > 0) {
                connectedChains += (deque.pollLast() + 1);
                connectRings--;
                answer++;
            } else {
                connectRings = deque.pollFirst();
            }
        }

        if (connectRings > 0) {
            connectedChains += connectRings;
            answer++;
        }

        System.out.println(answer);
    }
}
