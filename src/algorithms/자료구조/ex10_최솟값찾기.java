package algorithms.자료구조;

import org.w3c.dom.Node;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 백준 P11003
public class ex10_최솟값찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 출력을 버퍼에 넣고 한 번에 출력하기 위해 BufferedWriter 사용
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 입력 받을 데이터의 개수
        int L = Integer.parseInt(st.nextToken());   // 최솟값을 구하는 범위
        st = new StringTokenizer(br.readLine());
        Deque<Node> deque = new LinkedList<>();
        for (int i = 0; i < N; i++){
            int now = Integer.parseInt(st.nextToken());

            // 덱의 마지막 위치에서부터 now보다 큰 값은 덱에서 제거
            while (!deque.isEmpty() && deque.getLast().value > now) deque.removeLast();

            // 덱의 마지막 위치에 now 값 저장
            deque.addLast(new Node(now,i));

            // 덱의 첫번째 위치에서부터 L의 범위를 벗어난 값을 덱에서 제거
            if(deque.getFirst().index <= i - L) deque.removeFirst();

            // 덱의 1번째 데이터 출력하기
            bw.write(deque.getFirst().value + " ");
        }
        bw.flush();
        bw.close();
    }

    private static class Node {
        public int value;
        public int index;
        Node(int value, int index){
            this.value = value;
            this.index = index;
        }
    }
}
