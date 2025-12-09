package baekjoon.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class 눈사람 {

    static int N;
    static int[] snow;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        snow = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snow[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snow);
        search(new HashSet<>(), 0);

        System.out.println(answer);
    }

    public static void search(HashSet<Integer> selected, int target) {
        if (selected.size() == 2) {
            calculate(selected, target);
            return;
        }

        if (answer == 0) return;

        for (int i = 0; i < snow.length; i++) {
            if (selected.contains(i)) continue;

            selected.add(i);
            target += snow[i];
            search(selected, target);
            selected.remove(i);
            target -= snow[i];
        }
    }

    public static void calculate(HashSet<Integer> selected, int target) {
        int left = 0;
        int right = N - 1;

        while (left < right) {
            if (selected.contains(left)) {
                left++;
                continue;
            }

            if (selected.contains(right)) {
                right--;
                continue;
            }

            int snowman = snow[left] + snow[right];
            answer = Math.min(answer, Math.abs(snowman - target));
            if (answer == 0) return;

            if (snowman > target) right--;
            else left++;
        }
    }
}
