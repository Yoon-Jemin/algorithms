package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 올림픽 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[][] countries = new int[n][4];


        int goldK = 0;
        int silverK = 0;
        int bronzeK = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int countryNum = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            countries[i][0] = gold;
            countries[i][1] = silver;
            countries[i][2] = bronze;
            countries[i][3] = countryNum;

            if (countryNum == target) {
                goldK = gold;
                silverK = silver;
                bronzeK = bronze;
            }
        }

        int rank = 1;
        for (int i = 0; i < n; i++) {
            int gold = countries[i][0];
            int silver = countries[i][1];
            int bronze = countries[i][2];
            int countryNum = countries[i][3];

            if (countryNum == target) continue;

            if (gold > goldK) rank++;
            else if (gold == goldK && silver > silverK) rank++;
            else if (gold == goldK && silver == silverK && bronze > bronzeK) rank++;
        }

        System.out.println(rank);
    }
}