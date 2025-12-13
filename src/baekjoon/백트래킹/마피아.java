package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 마피아 {

    static int N;
    static int[][] R;
    static int[] guilty;
    static int mafia;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = new int[N][N];
        guilty = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            guilty[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        mafia = Integer.parseInt(st.nextToken());
        boolean[] dead = new boolean[N];
        Arrays.fill(dead, false);

        simulate(0, dead, N);
        System.out.println(answer);
    }

    private static void simulate(int day, boolean[] dead, int alive) {
        answer = Math.max(answer, day);
        if (alive == 1) return;

        if (alive % 2 == 1) {   // 낮
            int suspect = vote(dead);
            if (suspect == mafia) return;

            dead[suspect] = true;
            simulate(day, dead, alive - 1);
            dead[suspect] = false;
            return;
        }

        for (int kill = 0; kill < N; kill++) {  // 밤
            if (kill == mafia) continue;    // 스스로 죽일 수 없음
            if (dead[kill]) continue;  // 이미 죽은 사람을 죽일 수 없음

            killAndUpdate(kill);
            dead[kill] = true;

            simulate(day + 1, dead, alive - 1);

            undo(kill);
            dead[kill] = false;
        }
    }

    private static void killAndUpdate(int kill) {
        for (int i = 0; i < N; i++) {
            guilty[i] += R[kill][i];
        }
    }

    private static void undo(int kill) {
        for (int i = 0; i < N; i++) {
            guilty[i] -= R[kill][i];
        }
    }

    private static int vote(boolean[] dead) {
        int suspect = -1;
        int maxGuilt = 0;

        for (int i = 0; i < N; i++) {
            if (dead[i]) continue;

            if (guilty[i] > maxGuilt) {
                suspect = i;
                maxGuilt = guilty[i];
            }
        }

        return suspect;
    }
}
