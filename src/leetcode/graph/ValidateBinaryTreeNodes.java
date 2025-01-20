package leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ValidateBinaryTreeNodes {

    public static void main(String[] args) {
//        int n = 4;
//        int[] leftChild = {1,-1,3,-1};
//        int[] rightChild = {2,-1,-1,-1};
        int n = 4;
        int[] leftChild = {1,0,3,-1};
        int[] rightChild = {-1,-1,-1,-1};
        System.out.println(validateBinaryTreeNodes(n, leftChild, rightChild));
    }

    public static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        ArrayList<Integer>[] graph1 = new ArrayList[n];
        ArrayList<Integer>[] graph2 = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph1[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++){
            if (leftChild[i] != -1) {
                graph1[leftChild[i]].add(i);
                graph2[leftChild[i]].add(i);
                graph2[i].add(leftChild[i]);
            }
            if (rightChild[i] != -1) {
                graph1[rightChild[i]].add(i);
                graph2[rightChild[i]].add(i);
                graph2[i].add(rightChild[i]);
            }
        }

        int[] numParents = new int[n];
        for (int i = 0; i < n; i++) {
            numParents[i] = graph1[i].size();
        }

        int onlyOne = 0;
        int root = 0;
        for (int i = 0; i < n; i++) {
            if (numParents[i] == 1) continue;
            if (numParents[i] == 0){
                if (onlyOne == 0) {
                    onlyOne++;
                    root = i;
                }
                else return false;
            } else return false;
        }

        if (onlyOne == 0) return false;

        if (!BFS(graph2, root, n)) return false;

        return true;
    }

    public static boolean BFS(ArrayList<Integer>[] graph2, int root, int n){
        Boolean[] visited = new Boolean[n];
        Arrays.fill(visited, false);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);

        int count = 0;
        while (!queue.isEmpty()){
            int now = queue.poll();
            count++;
            visited[now] = true;
            for (int next : graph2[now]){
                if (!visited[next]) queue.add(next);
            }
        }

        return count == n;
    }
}
