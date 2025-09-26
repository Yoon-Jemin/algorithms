package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 우체국 {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        List<int[]> cities = new ArrayList<>();
        long totalPeople = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int city = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            cities.add(new int[] {city, people});
            totalPeople += people;
        }

        Collections.sort(cities, (a, b) -> a[0] - b[0]);

        long half = (totalPeople + 1) / 2;
        long leftPeople = 0;
        int answer = 0;
        for (int[] city : cities) {
            int cityNum = city[0];
            int cityPeople = city[1];

            leftPeople += cityPeople;

            if (half <= leftPeople) {
                answer = cityNum;
                break;
            }
        }

        System.out.println(answer);
    }
}
