package baekjoon.스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 스카이라인 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int n = Integer.parseInt(st.nextToken());
        int count = 0;

        for (int i = 0; i <= n; i++) {
            int idx;
            int height;
            if (i < n) {
                st = new StringTokenizer(br.readLine());
                idx = Integer.parseInt(st.nextToken());
                height = Integer.parseInt(st.nextToken());
            } else {
                height = 0;
            }

            if (stack.empty() || stack.peek() < height) {
                stack.push(height);
            } else {
                while (!stack.isEmpty() && stack.peek() > height) {
                    stack.pop();
                    count++;
                }

                if (stack.isEmpty()) {
                    if (height != 0) stack.push(height);
                } else {
                    if (stack.peek() != height) stack.push(height);
                }

            }
        }

        System.out.println(count);
    }
}
