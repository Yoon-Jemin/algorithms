package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 게리맨더링 {

    static int N;
    static int[] population;
    static ArrayList<Integer>[] map;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        population = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        map = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= num; j++) {
                map[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        divide(1, new ArrayList<>(), new ArrayList<>());
        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void divide(int index, ArrayList<Integer> group1, ArrayList<Integer> group2) {
        if (group1.size() + group2.size() == N) {
            if (group1.size() == 0 || group2.size() == 0) return;
            if (isPossible(group1) && isPossible(group2)) {
                int sum1 = getSum(group1);
                int sum2 = getSum(group2);
                answer = Math.min(answer, Math.abs(sum1 - sum2));
            }
            return;
        }

        for (int i = index; i <= N; i++) {
            group1.add(i);
            divide(i + 1, group1, group2);
            group1.remove(group1.size() - 1);

            group2.add(i);
            divide(i + 1, group1, group2);
            group2.remove(group2.size() - 1);
        }
    }

    private static int getSum(ArrayList<Integer> group) {
        int sum = 0;
        for (int g : group) {
            sum += population[g];
        }
        return sum;
    }

    private static boolean isPossible(ArrayList<Integer> group) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> setGroup = new HashSet<>();
        setGroup.addAll(group);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(group.get(0));
        visited.add(group.get(0));

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int neighbor : map[now]) {
                if (visited.contains(neighbor)) continue;
                if (setGroup.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return visited.size() == setGroup.size();
    }
}
