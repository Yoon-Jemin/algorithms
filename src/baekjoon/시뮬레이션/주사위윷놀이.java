package baekjoon.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 주사위윷놀이 {

    static class Node {
        int score;
        Node next;
        Node blueNext;

        Node (int score) {
            this.score = score;
        }
    }

    static class Board {
        Node start = new Node(0);
        Node finish = new Node(0);

        Board() {
            Node[] main = new Node[21];
            for (int i = 0; i < 21; i++) {
                main[i] = new Node(i * 2);
            }
            for (int i = 0; i < 20; i++) {
                main[i].next = main[i + 1];
            }
            main[20].next = finish;
            start.next = main[1];

            Node n13 = new Node(13);
            Node n16 = new Node(16);
            Node n19 = new Node(19);
            Node n25 = new Node(25);
            Node n30 = new Node(30);
            Node n35 = new Node(35);
            main[5].blueNext = n13;
            n13.next = n16;
            n16.next = n19;
            n19.next = n25;
            n25.next = n30;
            n30.next = n35;
            n35.next = main[20];

            Node n22 = new Node(22);
            Node n24 = new Node(24);
            main[10].blueNext = n22;
            n22.next = n24;
            n24.next = n25;

            Node n28 = new Node(28);
            Node n27 = new Node(27);
            Node n26 = new Node(26);
            main[15].blueNext = n28;
            n28.next = n27;
            n27.next = n26;
            n26.next = n25;
        }
    }

    static int[] dice;
    static Node[] horses;
    static Board board;
    static int maxScore = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dice = new int[10];
        horses = new Node[4];
        board = new Board();


        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(horses, board.start);
        dfs(0,0);
        System.out.println(maxScore);
    }

    private static void dfs(int turn, int score) {
        if (turn == 10) {
            maxScore = Math.max(maxScore, score);
            return;
        }

        for (int i = 0; i < 4; i++) {
            Node current = horses[i];

            if (current == board.finish) continue;

            Node next = move(current, dice[turn]);

            if (next == board.finish || !isOccupied(next)) {
                Node prevPos = horses[i];
                horses[i] = next;

                int gained = (next == board.finish ? 0 : next.score);
                dfs(turn + 1, score + gained);

                horses[i] = prevPos;
            }
        }
    }

    private static Node move(Node current, int steps) {
        Node node = current;

        if (node.blueNext != null) {
            node = node.blueNext;
            steps--;
        } else {
            node = node.next;
            steps--;
        }

        while (steps > 0 && node != null && node != board.finish) {
            node = node.next;
            steps--;
        }

        return (node == null) ? board.finish : node;
    }

    static boolean isOccupied(Node node) {
        if (node == board.finish || node == board.start) return false;
        for (Node h : horses) {
            if (h == node) return true;
        }

        return false;
    }
}
