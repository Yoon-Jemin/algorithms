package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 멍멍이쓰다듬기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int monkey = Integer.parseInt(st.nextToken());
        int dog = Integer.parseInt(st.nextToken());

        if (monkey == dog) {
            System.out.println(0);
            return;
        }

        int answer = 0;
        int diff = Math.abs(monkey - dog);
        int sqrDiff = (int) Math.sqrt(diff);

        if (sqrDiff * sqrDiff == diff) {
            answer = 2 * sqrDiff - 1;
        } else if (sqrDiff * sqrDiff + sqrDiff >= diff) {
            answer = 2 * sqrDiff;
        } else {
            answer = 2 * sqrDiff + 1;
        }

        System.out.println(answer);
    }
}
