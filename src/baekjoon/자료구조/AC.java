package baekjoon.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class AC {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());

        while (k-- > 0) {
            st = new StringTokenizer(br.readLine());
            String function = st.nextToken();   // D

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   // 배열에 들어있는 개수

            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();

            Deque<Integer> deque = new ArrayDeque<>();
            input = input.substring(1, input.length() - 1);

            if (input.length() > 0) {
                String[] strInput = input.split(",");
                for (String s : strInput) deque.add(Integer.parseInt(s));
            }

            boolean isReversed = false;
            boolean isError = false;
            for (int i = 0; i < function.length(); i++) {
                String f = function.substring(i, i + 1);

                if (f.equals("R")) {
                    isReversed = !isReversed;
                } else if (f.equals("D")) {
                    if (deque.isEmpty()) {
                        isError = true;
                        break;
                    } else {
                        if (isReversed) deque.pollLast();
                        else deque.pollFirst();
                    }
                }
            }

            if (isError) {
                System.out.println("error");
                continue;
            }

            if (deque.isEmpty()) {
                System.out.println("[]");
                continue;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while (!deque.isEmpty()) {
                if (isReversed) sb.append(deque.pollLast());
                else sb.append(deque.pollFirst());
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");

            System.out.println(sb.toString());
        }
    }
}
