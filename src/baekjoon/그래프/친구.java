package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 친구 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < n; j++) {
                if (s.charAt(j) == 'Y') {
                    arr[i][j] = 1;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int[] isFriend = new int[n];

            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (arr[i][j] == 1) {
                    isFriend[j] = 1;
                } else {
                    for (int k = 0; k < n; k++) {
                        if (arr[i][k] == 1 && arr[k][j] == 1) {
                            isFriend[j] = 1;
                            break;
                        }
                    }
                }
            }

            int count = 0;
            for (int is : isFriend) {
                if (is == 1) count++;
            }
            max = Math.max(max, count);
        }

        System.out.println(max);
    }
}
