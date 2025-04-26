package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 로또 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        while (true) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            List<Integer> set = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                set.add(Integer.parseInt(st.nextToken()));
            }

            backTrack(0, new ArrayList<>() ,set);
            System.out.println();
            st = new StringTokenizer(br.readLine());
        }
    }

    private static void backTrack(int index, List<Integer> group, List<Integer> set) {
        if (group.size() == 6) {
            for (int i = 0; i < group.size(); i++) {
                System.out.print(group.get(i) + " ");
            }
            System.out.println();
            return;
        }

        for (int i = index; i < set.size(); i++) {
            group.add(set.get(i));
            backTrack(i + 1,group,set);
            group.remove(group.size()-1);
        }
    }
}
