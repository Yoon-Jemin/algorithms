package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 줄세우기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int turn = Integer.parseInt(st.nextToken());
            int moves = 0;
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                int person = Integer.parseInt(st.nextToken());
                if (list.isEmpty()) {
                    list.add(person);
                } else if (list.get(list.size() - 1) > person) {
                    for (int k = 0; k < list.size(); k++) {
                        if (list.get(k) > person) {
                            list.add(k, person);
                            moves += list.size() - 1 - k;
                            break;
                        }
                    }
                } else {
                    list.add(person);
                }
            }
            System.out.println(turn + " " + moves);
        }
    }
}
