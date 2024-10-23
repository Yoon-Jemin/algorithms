package review;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class 길찾기게임2 {

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
        int num;

        Node right;
        Node left;

        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static ArrayList<Node> nodes;
    static int cnt = 0;
    static int[][] answer;
    public static int[][] solution(int[][] nodeinfo) {

        nodes = new ArrayList<>();

        for(int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i +1));
        }

        Collections.sort(nodes, (o1, o2) -> (o2.y - o1.y));

        Node root = nodes.get(0);
        for(int i = 1; i < nodes.size(); i++) {
            update(root, nodes.get(i));
        }

        answer = new int[2][nodeinfo.length];
        pre(root);
        cnt = 0;
        post(root);

        return answer;
    }

    public static void pre(Node node) {
        answer[0][cnt++] = node.num;
        if(node.left != null) {
            pre(node.left);
        }
        if(node.right != null) {
            pre(node.right);
        }
    }

    public static void post(Node node) {
        if(node.left != null) {
            post(node.left);
        }
        if(node.right != null) {
            post(node.right);
        }
        answer[1][cnt++] = node.num;
    }

    public static void update(Node parent, Node child) {
        if(parent.x < child.x) {    // 왼쪽 자식
            if(parent.right == null){
                parent.right = child;
            } else {
                update(parent.right, child);
            }
        } else {    // 오른쪽 자식
            if(parent.left == null){
                parent.left = child;
            } else {
                update(parent.left, child);
            }
        }
    }

}
