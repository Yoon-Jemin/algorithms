package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 댄스파티 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 사람의 수
        int answer = 0;

        List<Integer> malePreferSmall = new ArrayList<>();
        List<Integer> malePreferBig = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());

            if (height > 0) malePreferBig.add(height);
            else malePreferSmall.add(height);
        }
        Collections.sort(malePreferBig, Comparator.reverseOrder());
        Collections.sort(malePreferSmall);

        List<Integer> femalePreferSmall = new ArrayList<>();
        List<Integer> femalePreferBig = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());

            if (height > 0) femalePreferBig.add(height);
            else femalePreferSmall.add(height);
        }
        Collections.sort(femalePreferBig, Comparator.reverseOrder());
        Collections.sort(femalePreferSmall);

        answer += greedy(malePreferSmall, femalePreferBig);
        answer += greedy(femalePreferSmall, malePreferBig);

        System.out.println(answer);
    }

    private static int greedy(List<Integer> malePreferSmall, List<Integer> femalePreferBig) {
        int couple = 0;
        int wPointer = 0;

        for (int mPointer = 0; mPointer < malePreferSmall.size(); mPointer++) {
            int male = Math.abs(malePreferSmall.get(mPointer));

            while (wPointer < femalePreferBig.size()) {
                int female = femalePreferBig.get(wPointer);
                if (male > female) {
                    couple++;
                    wPointer++;
                    break;
                }

                wPointer++;
            }

            if (wPointer >= femalePreferBig.size()) break;
        }

        return couple;
    }
}
