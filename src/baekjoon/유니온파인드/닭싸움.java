package baekjoon.유니온파인드;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 닭싸움 {

    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        ArrayList<Integer>[] enemyList = new ArrayList[N + 1];
        ArrayList<Integer>[] friendList = new ArrayList[N + 1];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            friendList[i] = new ArrayList<>();
            enemyList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            String relation = sc.next();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (relation.equals("E")) {     // 원수
                enemyList[a].add(b);
                enemyList[b].add(a);
            } else {    // 친구
                friendList[a].add(b);
                friendList[b].add(a);
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < enemyList[i].size(); j++) {
                int n = enemyList[i].get(j);    // i의 원수
                for (int k = 0; k < enemyList[n].size(); k++) { // i의 원수 n의 원수
                    if (enemyList[n].get(k) == i) continue;

                    friendList[i].add(enemyList[n].get(k));
                    friendList[enemyList[n].get(k)].add(i);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < friendList[i].size(); j++) {
                union(i, friendList[i].get(j));
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) parent[b] = a;
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
}
