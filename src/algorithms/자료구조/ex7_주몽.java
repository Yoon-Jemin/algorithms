package algorithms.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 P1940
public class ex7_주몽 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());    // 재료의 개수
        int M = Integer.parseInt(bf.readLine());    // 갑옷을 만드는 데 필요한 수

        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        int i = 0;
        int j = N-1;
        int count = 0;

        while (i < j){
            if(A[i] + A[j] == M){
                i++; j--; count++;
            } else if (A[i] + A[j] < M) {
                i++;
            } else if (A[i] + A[j] > M) {
                j++;
            }
        }

        System.out.println(count);
        bf.close();

    }
}
