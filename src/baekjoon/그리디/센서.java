package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 센서 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   // 센서의 개수

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());   // 집중국의 개수

        if (k >= n) {
            System.out.println(0);
            return;
        }

        st = new StringTokenizer(br.readLine());
        List<Integer> sensors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sensors.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(sensors);

        int total = 0;
        List<Integer> diffs = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int diff = sensors.get(i + 1) - sensors.get(i);
            diffs.add(diff);
            total += diff;
        }
        Collections.sort(diffs, Collections.reverseOrder());

        int minus = 0;
        for (int i = 0; i < k - 1; i++) {
            minus += diffs.get(i);
        }

        System.out.println(total - minus);
    }
}
