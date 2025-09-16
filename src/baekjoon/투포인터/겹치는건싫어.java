package baekjoon.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 겹치는건싫어 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1) {
            System.out.println(1);
            return;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int rPointer = 0;
        int lPointer = 1;
        map.put(arr[rPointer], map.getOrDefault(arr[rPointer], 0) + 1);

        int answer = 1;
        while (lPointer < n) {
            map.put(arr[lPointer], map.getOrDefault(arr[lPointer], 0) + 1);

            if (map.get(arr[lPointer]) > k) {
                while (map.get(arr[lPointer]) > k) {
                    map.put(arr[rPointer], map.getOrDefault(arr[rPointer], 0) - 1);
                    rPointer++;
                }
            }

            answer = Math.max(answer, lPointer - rPointer + 1);
            lPointer++;
        }

        System.out.println(answer);
    }
}
