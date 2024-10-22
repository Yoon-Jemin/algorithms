package programmers.level_3;

import java.util.*;

public class 길찾기게임 {

    public static void main(String[] args) {

        int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
        int[][] result = solution(nodeinfo);

        for(int i = 0; i < result.length; i++) {
            System.out.println(i + ":");
            for(int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Node{
        int x;
        int y;
        int v;

        Node left;
        Node right;
        public Node(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }
    }

    static ArrayList<Node> nodes = new ArrayList<>();
    static int cnt = 0;
    static int [][] answer;
    public static int[][] solution(int[][] nodeinfo) {

        int size = nodeinfo.length;

        for(int i = 0; i < size; i++) {
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }

        // depth 기준 오름차순 정렬
        Collections.sort(nodes, (o1, o2) -> o2.y - o1.y);

        Node root = nodes.get(0);
        for(int i = 1; i < size; i++) {
            update(root, nodes.get(i));
        }
        answer = new int [2][size];
        pre(root);
        cnt = 0;
        post(root);
        return answer;
    }

    private static void post(Node cur) {
        if(cur.left != null) {
            post(cur.left);
        }
        if(cur.right != null) {
            post(cur.right);
        }
        answer[1][cnt++] = cur.v;
    }

    private static void pre(Node cur) {
        answer[0][cnt++] = cur.v;
        if(cur.left != null) {
            pre(cur.left);
        }
        if(cur.right != null) {
            pre(cur.right);
        }
    }

    private static void update(Node parent, Node child) {
        if(parent.x > child.x) {
            if(parent.left == null) parent.left = child;
            else update(parent.left, child);
        }
        else{
            if(parent.right == null) parent.right = child;
            else update(parent.right, child);
        }
    }


}
