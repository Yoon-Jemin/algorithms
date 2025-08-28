package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 계란으로계란치기 {

    static class Egg {
        int strength;
        int weight;

        Egg(int strength, int weight) {
            this.strength = strength;
            this.weight = weight;
        }
    }

    static int N;
    static int maxBroken = 0;
    static Egg[] eggs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        eggs = new Egg[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        backTrack(0);
        System.out.println(maxBroken);
    }

    private static void backTrack(int now) {
        if (now == N) {
            int brokenCount = 0;
            for (int i = 0; i < N; i++) {
                if (eggs[i].strength <= 0) brokenCount++;
            }

            maxBroken = Math.max(maxBroken, brokenCount);
            return;
        }

        if (eggs[now].strength <= 0) {
            backTrack(now + 1);
            return;
        }

        boolean hit = false;
        for (int i = 0; i < N; i++) {
            if (i == now || eggs[i].strength <= 0) continue;

            hit = true;
            eggs[now].strength -= eggs[i].weight;
            eggs[i].strength -= eggs[now].weight;

            backTrack(now + 1);

            eggs[now].strength += eggs[i].weight;
            eggs[i].strength += eggs[now].weight;
        }

        if (!hit) backTrack(now + 1);
    }
}
