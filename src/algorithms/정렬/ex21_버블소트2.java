package algorithms.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// P1517 버블소트2
public class ex21_버블소트2 {
    public static int[] A, tmp;
    public static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());    // 정렬할 수의 개수
        A = new int[N + 1];   // 정렬할 배열
        tmp = new int[N + 1];    // 정렬할 때 잠시 사용할 임시 배열
        result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        merge_sort(1, N);
        System.out.println(result);
    }

    private static void merge_sort(int s, int e) {
        if(e - s < 1) return;
        int start = s;
        int end = e;
        int mid = start + (end - start)/2;

        merge_sort(start, mid);
        merge_sort(mid + 1, end);

        for(int i = start; i <= end; i++){
            tmp[i] = A[i];
        }

        int index1 = start;     // 첫 번째 그룹의 포인터
        int index2 = mid + 1;   // 두 번째 그룹의 포인터
        int k = s;

        while(index1 <= mid && index2 <= end){  // 두 그룹을 병합하는 로직
            if(tmp[index1] > tmp[index2]){
                A[k] = tmp[index2];
                result = result + index2 - k;
                index2++;
                k++;
            }else {
                A[k] = tmp[index1];
                index1++;
                k++;
            }
        }

        while (index1 <= mid){
            A[k] = tmp[index1];
            index1++;
            k++;
        }
        while (index2 <= end){
            A[k] = tmp[index2];
            index2++;
            k++;
        }


    }
}
