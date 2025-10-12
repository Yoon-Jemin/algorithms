package baekjoon.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 개미굴 {

    public static class Food {
        String foodName;
        Map<String, Food> child = new TreeMap<>();

        public Food (String foodName) {
            this.foodName = foodName;
        }

    }

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 먹이의 정보 개수

        Food root = new Food("");

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());

            Food parent = root;
            for (int j = 0; j < m; j++) {
                String foodName = st.nextToken();

                parent.child.putIfAbsent(foodName, new Food(foodName));
                parent = parent.child.get(foodName);
            }
        }

        print(root, 0);
        System.out.println(sb.toString());
    }

    public static void print(Food food, int depth) {
        for (String key : food.child.keySet()) {
            for (int i = 0; i < depth; i++) sb.append("--");
            sb.append(key).append("\n");

            print(food.child.get(key), depth + 1);
        }
    }
}
