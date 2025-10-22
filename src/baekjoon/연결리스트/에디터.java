package baekjoon.연결리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 에디터 {

    public static class Node {
        Node left, right;
        char word;

        public Node (char word) {
            this.word = word;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String input = st.nextToken();

        List<Node> words = new ArrayList<>();
        words.add(new Node(' '));
        for (int i = 0; i < input.length(); i++) {
            words.add(new Node(input.charAt(i)));
        }

        for (int i = 1; i < words.size(); i++) {
            words.get(i).left = words.get(i - 1);
            words.get(i - 1).right = words.get(i);
        }

        Node cursor = words.get(words.size() - 1);

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("L")) {  // 커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
                if (cursor.word == ' ') continue;
                cursor = cursor.left;
            } else if (command.equals("D")) {   // 커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
                if (cursor.right == null) continue;
                cursor = cursor.right;
            } else if (command.equals("B")) {   // 커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
                if (cursor.word == ' ') continue;
                cursor = cursor.left;
                cursor.right = cursor.right.right;
                if (cursor.right != null) cursor.right.left = cursor;
            } else {    // 문자를 커서 왼쪽에 추가함
                Node newNode = new Node(st.nextToken().charAt(0));
                newNode.left = cursor;
                newNode.right = cursor.right;

                newNode.left.right = newNode;
                if (newNode.right != null) newNode.right.left = newNode;
                cursor = cursor.right;
            }
        }

        while (cursor.word != ' ') {
            cursor = cursor.left;
        }

        StringBuilder sb = new StringBuilder();
        cursor = cursor.right;
        while (cursor != null) {
            sb.append(cursor.word);
            cursor = cursor.right;
        }

        System.out.println(sb.toString());
    }
}
