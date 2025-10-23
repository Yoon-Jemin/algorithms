package baekjoon.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 트리순회 {

    public static class Node {
        Node parent = null;
        Node left = null;
        Node right = null;
        int number;
        boolean isVisited;

        public Node (int number) {
            this.number = number;
            this.isVisited = false;
        }
    }

    static int N;
    static int lastNode;
    static boolean stop = false;
    static int moveCount = 0;
    public static void main(String[] args) throws IOException {     // TODO: 다시 풀어보기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <=N; i++) nodes[i] = new Node(i);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int leftChild = Integer.parseInt(st.nextToken());
            int rightChild = Integer.parseInt(st.nextToken());

            if (leftChild != -1) {
                nodes[parent].left = nodes[leftChild];
                nodes[leftChild].parent = nodes[parent];
            }
            if (rightChild != -1) {
                nodes[parent].right = nodes[rightChild];
                nodes[rightChild].parent = nodes[parent];
            }
        }

        List<Integer> in = new ArrayList<>();
        inOrder(nodes[1], in);
        lastNode = in.get(in.size() - 1);

        simulate(nodes[1]);

        System.out.println(moveCount);
    }

    private static void simulate(Node node) {
        if (node == null || stop) return;

        if (node.left != null && !stop) {
            moveCount++;
            simulate(node.left);
            if (stop) return;
            moveCount++;
        }

        if (node.number == lastNode) {
            stop = true;
            return;
        }

        if (node.right != null && !stop) {
            moveCount++;
            simulate(node.right);
            if (stop) return;
            moveCount++;
        }
    }

    private static void inOrder(Node node, List<Integer> list) {
        if (node == null) return;
        inOrder(node.left, list);
        list.add(node.number);
        inOrder(node.right, list);
    }
}
