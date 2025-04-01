package baekjoon.바킹독;

import java.io.IOException;
import java.util.*;

public class 거짓말 {

    public static int[] parents;
    public static List<List<Integer>> parties = new ArrayList<List<Integer>>();
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();   // 사람의 수
        int m = sc.nextInt();   // 파티의 수

        int numTruth = sc.nextInt();
        List<Integer> truthGroup = new LinkedList<>();
        for (int i = 0; i < numTruth; i++) {
            truthGroup.add(sc.nextInt());
        }

        if (numTruth == 0) {
            System.out.println(m);
            return;
        }

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int numParty = sc.nextInt();
            List<Integer> party = new ArrayList<>();
            int firstPerson = sc.nextInt();

            party.add(firstPerson);
            for (int j = 1; j < numParty; j++ ) {
                int nextPerson = sc.nextInt();
                party.add(nextPerson);
                union(firstPerson, nextPerson);
            }
            parties.add(party);
        }

        int truthRoot = -1;
        for (int truthPerson : truthGroup) {
            if (truthRoot == -1) truthRoot = find(truthPerson);
            else union(truthRoot, truthPerson);
        }

        int count = 0;
        for (List<Integer> party : parties) {
            int leader = find(party.get(0));
            boolean canLie = true;

            for (int truthPerson : truthGroup) {
                if (find(truthPerson) == leader) {
                    canLie = false;
                    break;
                }
            }

            if (canLie) count++;
        }

        System.out.println(count);
    }

    private static int find(int x) {
        if (parents[x] != x) return parents[x] = find(parents[x]);
        else return x;
    }

    private static void union(int firstPerson, int secondPerson) {
        int rootA = find(firstPerson);
        int rootB = find(secondPerson);
        if (rootA != rootB) parents[rootB] = rootA;
    }
}
