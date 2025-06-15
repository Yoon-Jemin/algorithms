package baekjoon.수학;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 어두운굴다리 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int bridgeLength = sc.nextInt();
        int numStreetLight = sc.nextInt();
        List<Integer> streetLights = new ArrayList<>();

        for (int i = 0; i < numStreetLight; i++) streetLights.add(sc.nextInt());
        Collections.sort(streetLights);

        int maxDistance = 0;
        for (int i = 0; i < streetLights.size(); i++) {
            int nowLocation = streetLights.get(i);

            if (i == 0) {
                maxDistance = Math.max(maxDistance, nowLocation);
            } else {
                int prevLocation = streetLights.get(i - 1);
                maxDistance = Math.max(maxDistance, (nowLocation - prevLocation + 1) / 2);
            }

            if (i == streetLights.size() - 1) {
                maxDistance = Math.max(maxDistance, bridgeLength - nowLocation);
            }
        }

        System.out.println(maxDistance);
    }
}
