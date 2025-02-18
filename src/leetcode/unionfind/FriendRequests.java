package leetcode.unionfind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FriendRequests {

    public static void main(String[] args) {
        int n = 5;
        int[][] restrictions = {{0,1},{1,2},{2,3}};
        int[][] requests = {{0,4},{1,2},{3,1},{3,4}};
        boolean[] result = friendRequests(n, restrictions, requests);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        Set<Integer>[] restricts = new HashSet[n];
        for (int i = 0; i < n; i++) {
            restricts[i] = new HashSet<>();
        }

        for (int[] restriction : restrictions) {
            restricts[restriction[0]].add(restriction[1]);
            restricts[restriction[1]].add(restriction[0]);
        }

        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        boolean[] result = new boolean[requests.length];
        for (int i = 0; i < requests.length; i++) {
            result[i] = isPossible(requests[i][0], requests[i][1], parent, size, restricts);
        }

        return result;
    }

    public static boolean isPossible(int a, int b, int[] parent, int[] size, Set<Integer>[] restricts) {
        int parent1 = find(a, parent);
        int parent2 = find(b, parent);

        boolean result = true;
        for (int restrict : restricts[parent1]) {
            if (find(restrict, parent) == parent2) result = false;
        }
        for (int restrict : restricts[parent2]) {
            if (find(restrict, parent) == parent1) result = false;
        }

        if (result && parent1 != parent2) {
            if (size[parent1] < size[parent2]) {
                int temp = parent1;
                parent1 = parent2;
                parent2 = temp;
            }

            parent[parent2] = parent1;
            size[parent1] += size[parent2];
            restricts[parent1].addAll(restricts[parent2]);
        }

        return result;
    }

    public static int find(int a, int[] parent) {
        if (parent[a] != a) {
            parent[a] = find(parent[a], parent);
        }
        return parent[a];
    }
}
