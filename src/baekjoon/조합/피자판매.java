package baekjoon.조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 피자판매 {

    static int answer = 0;
    static int customer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        customer = Integer.parseInt(st.nextToken());    // 손님이 구매하고자 하는 피자의 크기

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());  // A 피자의 조각의 개수
        int b = Integer.parseInt(st.nextToken());  // B 피자의 조각의 개수

        int[] pizzaA = new int[a];
        int[] pizzaB = new int[b];

        for (int i = 0; i < a; i++) {
            st = new StringTokenizer(br.readLine());
            pizzaA[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(br.readLine());
            pizzaB[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> pizzaAMap = getSortedList(pizzaA);
        Map<Integer, Integer> pizzaBMap = getSortedList(pizzaB);
        if (pizzaAMap.containsKey(customer)) answer += pizzaAMap.get(customer);
        if (pizzaBMap.containsKey(customer)) answer += pizzaBMap.get(customer);

        Set<Integer> pizzaBSet = new HashSet<>(pizzaBMap.keySet());
        for (int pizza : pizzaAMap.keySet()) {
            if (pizza > customer) continue;

            if (pizzaBSet.contains(customer - pizza)) {
                answer += (pizzaAMap.get(pizza) * pizzaBMap.get(customer - pizza));
            }
        }

        System.out.println(answer);
    }

    private static Map<Integer, Integer> getSortedList(int[] pizza) {
        int[] newPizza = new int[pizza.length * 2];

        Map<Integer, Integer> map = new HashMap<>();

        // 길이 두 배로 증가
        for (int i = 0; i < pizza.length; i++) {
            newPizza[i] = pizza[i];
            newPizza[i + pizza.length] = pizza[i];
        }

        for (int size = 1; size < pizza.length; size++) {
            for (int start = 0; start < pizza.length; start++) {
                int sum = 0;
                for (int idx = start; idx < start + size; idx++) {
                    sum += newPizza[idx];
                }
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int total = 0;
        for (int p : pizza) total += p;
        map.put(total, 1);

        return map;
    }
}
