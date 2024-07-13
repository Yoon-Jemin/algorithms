package algorithms.자료구조;

import java.io.*;
import java.util.Stack;

// 백준 P17928
public class ex12_오큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());    // 수열의 개수
        int[] A = new int[N];
        int[] answer = new int[N];

        // 수열 배열 초기화
        String[] str = bf.readLine().split(" ");
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(str[i]);
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0);  // 최초 스택 초기화

        for(int i = 0; i < N; i++){
            while (!stack.isEmpty() && A[i] > A[stack.peek()]){
                answer[stack.pop()] = A[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            answer[stack.pop()] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < N; i++){
            bw.write(answer[i] + " ");
        }
        bw.write("\n");
        bw.flush();
    }
}
