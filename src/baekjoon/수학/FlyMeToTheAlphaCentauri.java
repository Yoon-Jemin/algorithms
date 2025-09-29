package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FlyMeToTheAlphaCentauri {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int dist = end - start;     // 4
            int sqrDist = (int) Math.sqrt(dist);    // 2

            if (sqrDist * sqrDist == dist) {
                System.out.println(2 * sqrDist - 1);
            } else if (sqrDist * sqrDist + sqrDist >= dist) {
                System.out.println(2 * sqrDist);
            } else {
                System.out.println(2 * sqrDist + 1);
            }
        }
    }
}
