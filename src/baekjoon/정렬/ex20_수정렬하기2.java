package baekjoon.정렬;

import java.io.*;

// 백준 P2751
public class ex20_수정렬하기2 {

    public static int[] A, temp;
    public static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        A = new int[N];
        temp = new int[N];

        for(int i =0; i < N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        // 병합 정렬 수행
        mergeSort(0, N-1);

        for(int i = 0; i < N; i++){
            bw.write(A[i] + "\n");
        }
        bw.flush();
        bw.close();
    }
    private static void mergeSort(int start, int end) {
        if(start >= end) return;
        int mid = start + (end - start)/2;

        mergeSort(start, mid);
        mergeSort(mid+1, end);

        for(int i = start; i <= end; i++){
            temp[i] = A[i];
        }

        // 두 그룹을 병합하는 로직
        int index1 = start;
        int index2 = mid + 1;

        int i = start;
        while(index1 <= mid && index2 <= end){
            if(temp[index1] >= temp[index2]){
                A[i] = temp[index2];
                i++; index2++;
            } else {
                A[i] = temp[index1];
                i++; index1++;
            }
        }

        while(index1 <= mid){
            A[i] = temp[index1];
            i++; index1++;
        }

        while(index2 <= end){
            A[i] = temp[index2];
            i++; index2++;
        }
    }
}
