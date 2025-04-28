package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수이어쓰기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String given = st.nextToken();

        int num = 1;
        while (given.length() > 0) {
            String strNum = String.valueOf(num);
            for (int i = 0; i < strNum.length(); i++) {
                if (given.charAt(0) == strNum.charAt(i)) {
                    given = given.substring(1);
                    if (given.length() == 0) break;
                }
            }
            num++;
        }

        System.out.println(num - 1);
    }
}

