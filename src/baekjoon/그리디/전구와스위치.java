package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 전구와스위치 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] now = new int[n];
        int[] desired = new int[n];

        st = new StringTokenizer(br.readLine());
        String strNow = st.nextToken();
        for (int i = 0; i < n; i++) {
            now[i] = Integer.parseInt(strNow.substring(i, i + 1));
        }

        st = new StringTokenizer(br.readLine());
        String strDesired = st.nextToken();
        for (int i = 0; i < n; i++) {
            desired[i] = Integer.parseInt(strDesired.substring(i, i + 1));
        }

        int answer1 = 1;    // 왼쪽을 누름
        int[] copyNow1 = new int[n];
        System.arraycopy(now, 0, copyNow1, 0, n);
        copyNow1[0] = 1 - copyNow1[0];
        copyNow1[1] = 1 - copyNow1[1];
        for (int i = 1; i < n; i++) {
            if (copyNow1[i - 1] == desired[i - 1]) {     // 이전 상태가 맞음
                continue;
            } else {    // 이전 상태가 안맞음 -> 무조건 눌러야 함
                copyNow1[i] = 1 - copyNow1[i];
                copyNow1[i - 1] = 1 - copyNow1[i - 1];
                if (i + 1 < n) copyNow1[i + 1] = 1 - copyNow1[i + 1];
                answer1++;
            }
        }
        if (copyNow1[n - 1] != desired[n - 1]) answer1 = -1;


        int answer2 = 0;    // 왼쪽을 안누름
        int[] copyNow2 = new int[n];
        System.arraycopy(now, 0, copyNow2, 0, n);
        for (int i = 1; i < n; i++) {
            if (copyNow2[i - 1] == desired[i - 1]) {     // 이전 상태가 맞음
                continue;
            } else {    // 이전 상태가 안맞음 -> 무조건 눌러야 함
                copyNow2[i] = 1 - copyNow2[i];
                copyNow2[i - 1] = 1 - copyNow2[i - 1];
                if (i + 1 < n) copyNow2[i + 1] = 1 - copyNow2[i + 1];
                answer2++;
            }
        }
        if (copyNow2[n - 1] != desired[n - 1]) answer2 = -1;

        if (answer1 == -1 && answer2 == -1) System.out.println(-1);
        else if (answer1 == -1 || answer2 == -1) System.out.println(Math.max(answer1, answer2));
        else System.out.println(Math.min(answer1, answer2));
    }
}
