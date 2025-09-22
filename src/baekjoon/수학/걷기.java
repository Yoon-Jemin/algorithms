package baekjoon.수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 걷기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());   // 그냥 이동
        int d = Integer.parseInt(st.nextToken());   // 대각선

        int distanceToMove = x + y;
        long answer = 0;

        if (x ==0 || y ==0 ){
            int distanceLeft = Math.abs(x - y);
            if (distanceLeft % 2 == 0) {
                answer += Math.min((long) distanceLeft * d, (long) distanceLeft * w);
            } else {
                answer += Math.min((long) (distanceLeft - 1) * d, (long) (distanceLeft - 1)  * w);
                answer += w;
            }
        } else if (w + w > d) {    // 대각선 타는게 이득
            int numShortCut = Math.min(x, y);
            answer += (long) numShortCut * d;

            int distanceLeft = Math.abs(x - y);
            if (distanceLeft % 2 == 0) {
                answer += Math.min((long) distanceLeft * d, (long) distanceLeft * w);
            } else {
                answer += Math.min((long) (distanceLeft - 1) * d, (long) (distanceLeft - 1)  * w);
                answer += w;
            }
        } else {
            answer = (long) distanceToMove * w;
        }

        System.out.println(answer);
    }
}
