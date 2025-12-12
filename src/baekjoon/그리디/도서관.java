package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 도서관 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 책의 개수
        int M = Integer.parseInt(st.nextToken());   // 한번에 들고갈 수 있는 책의 개수

        int[] books = new int[N];
        boolean[] isReturned = new boolean[N];
        int total = 0;
        int maxTrip = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            books[i] = Integer.parseInt(st.nextToken());
            isReturned[i] = false;
        }

        Arrays.sort(books);

        int carry = 0;
        int trip = 0;
        for (int i = 0; i < N; i++) {   // 음의 영역
            if (books[i] >= 0) break;

            if (carry == M) {   // 들고 갈 수 있는 범위를 꽉채움
                total += trip * 2;
                maxTrip = Math.max(maxTrip, trip);
                carry = 0;
                trip = 0;
            }

            trip = Math.max(trip, books[i] * -1);
            isReturned[i] = true;
            carry++;
        }

        if (carry > 0) {
            total += trip * 2;
            maxTrip = Math.max(maxTrip, trip);
            carry = 0;
            trip = 0;
        }

        for (int i = N - 1; i >= 0; i--) {  // 양의 영역
            if (books[i] <= 0) break;

            if (carry == M) {   // 들고 갈 수 있는 범위를 꽉채움
                total += trip * 2;
                maxTrip = Math.max(maxTrip, trip);
                carry = 0;
                trip = 0;
            }

            trip = Math.max(trip, books[i]);
            isReturned[i] = true;
            carry++;
        }

        if (carry > 0) {
            total += trip * 2;
            maxTrip = Math.max(maxTrip, trip);
            carry = 0;
            trip = 0;
        }

        System.out.println(total - maxTrip);
    }
}
