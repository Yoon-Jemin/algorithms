package leetcode.unionfind;

import java.util.*;

public class AccountMerge {

    public static void main(String[] args) {
        List<List<String>> accounts = List.of(
                List.of("John","johnsmith@mail.com","john_newyork@mail.com"),
                List.of("John","johnsmith@mail.com","john00@mail.com"),
                List.of("Mary","mary@mail.com"),
                List.of("John","johnnybravo@mail.com")
        );
        System.out.println(accountsMerge(accounts));
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailMap = new HashMap<>();    // 이메일 -> 숫자
        Map<String, String> emailNameMap = new HashMap<>();     // 이메일 -> 이름

        int emailIndex = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (emailMap.containsKey(email)) continue;
                emailMap.put(email, emailIndex);
                emailIndex++;
                emailNameMap.put(email, name);
            }
        }

        int[] parent = new int[emailIndex];
        for (int i = 0; i < emailIndex; i++) {
            parent[i] = i;
        }

        for (List<String> account : accounts) {
            int firstIndex = emailMap.get(account.get(1));
            for (int i = 2; i < account.size(); i++) {
                int nextIndex = emailMap.get(account.get(i));
                union(parent, firstIndex, nextIndex);
            }
        }

        Map<Integer, List<String>> rootToEmails = new HashMap<>();
        for (String email : emailMap.keySet()) {
            int index = emailMap.get(email);
            int root = find(index, parent);
            rootToEmails.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }

        List<List<String>> results = new ArrayList<>();
        for (List<String> list : rootToEmails.values()) {
            Collections.sort(list);
            String name = emailNameMap.get(list.get(0));
            List<String> result = new ArrayList<>();
            result.add(name);
            result.addAll(list);
            results.add(result);
        }

        return results;
    }

    private static void union(int[] parent, int firstIndex, int nextIndex) {
        int rootA = find(firstIndex, parent);
        int rootB = find(nextIndex, parent);

        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

    private static int find(int index, int[] parent) {
        if (parent[index] != index) {
            parent[index] = find(parent[index], parent);
        }

        return parent[index];
    }
}
