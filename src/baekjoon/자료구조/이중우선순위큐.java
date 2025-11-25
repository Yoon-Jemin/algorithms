package baekjoon.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 이중우선순위큐 {

    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 데이터 삭제할 때 연산 명령에 따라 우선순위가 가장 높은 데이터 또는 가장 낮은 데이터 중 하나를 삭제
        // 데이터 삽입: I
        // 데이터 삭제
            // 우선순위가 가장 높은 것을 삭제: D 1
            // 우선순위가 가장 낮은 것을 삭제: D -1

        T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());   // Q에 적용할 연산의 개수

            Map<Integer, Integer> map = new HashMap<>();
            PriorityQueue<Integer> minQ = new PriorityQueue<>();
            PriorityQueue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String op1 = st.nextToken();

                if (op1.equals("I")) {  // 숫자 추가
                    int num = Integer.parseInt(st.nextToken());

                    minQ.add(num);
                    maxQ.add(num);
                    map.put(num, map.getOrDefault(num, 0) + 1);
                }

                if (op1.equals("D")) {
                    int op2 = Integer.parseInt(st.nextToken());

                    if (op2 == 1) {     // 최댓값 삭제
                        while (!maxQ.isEmpty() && !map.containsKey(maxQ.peek())) {
                            maxQ.poll();
                        }

                        if (maxQ.isEmpty()) continue;
                        int max = maxQ.poll();
                        if (map.get(max) == 1) {
                            map.remove(max);
                        } else {
                            map.put(max, map.get(max) - 1);
                        }
                    } else {    // 최솟값 삭제
                        while (!minQ.isEmpty() && !map.containsKey(minQ.peek())) {
                            minQ.poll();
                        }

                        if (minQ.isEmpty()) continue;
                        int min = minQ.poll();
                        if (map.get(min) == 1) {
                            map.remove(min);
                        } else {
                            map.put(min, map.get(min) - 1);
                        }
                    }
                }
            }

            while (!maxQ.isEmpty() && !map.containsKey(maxQ.peek())) {
                maxQ.poll();
            }

            while (!minQ.isEmpty() && !map.containsKey(minQ.peek())) {
                minQ.poll();
            }

            // 결과 출력
            if (map.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(maxQ.peek() + " " + minQ.peek());
            }
        }
    }
}
