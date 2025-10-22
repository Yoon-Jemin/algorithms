package programmers.level_3;

import baekjoon.유니온파인드.WateringFields;

import java.util.*;

public class 표편집 {

    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        System.out.println(solution(n, k, cmd));
    }

    public static class Node {
        Node prev, next;
        int idx;
        boolean alive = true;

        Node (int idx) {
            this.idx = idx;
        }
    }

    public static String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Node(i);

        for (int i = 1; i < n; i++) {
            nodes[i].prev = nodes[i - 1];
            nodes[i - 1].next = nodes[i];
        }

        Node cur = nodes[k];
        Stack<Node> stack = new Stack<>();

        for (String c : cmd) {
            char op = c.charAt(0);

            if (op == 'U') {
                int x = Integer.parseInt(c.substring(2));
                while (x-- > 0) cur = cur.prev;
            } else if (op == 'D') {
                int x = Integer.parseInt(c.substring(2));
                while (x-- > 0) cur = cur.next;
            } else if (op == 'C') {
                stack.push(cur);
                cur.alive = false;

                Node p = cur.prev;
                Node q = cur.next;

                if (p != null) p.next = q;
                if (q != null) {
                    q.prev = p;
                    cur = q;
                } else {    // 마지막 행
                    cur = p;
                }
            } else {
                Node r = stack.pop();
                r.alive = true;

                Node p = r.prev;
                Node q = r.next;

                if (p != null) p.next = r;
                if (q != null) q.prev = r;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append(node.alive ? 'O' : 'X');
        }

        return sb.toString();
    }

}
