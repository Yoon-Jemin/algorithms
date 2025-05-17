package leetcode.array;

public class ConstructArray {

    public static void main(String[] args) {
        int[] result = constructArray(5, 2);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] constructArray(int n, int k) {
        int[] answer = new int[n];


        int low = 1;
        int high = n;
        for (int i = 0; i < k; i++) {
            if (i % 2 == 0) answer[i] = low++;
            else answer[i] = high--;
        }

        if (k % 2 == 0) {
            for (int i = k; i < n; i++) {
                answer[i] = high--;
            }
        } else {
            for (int i = k; i < n; i++) {
                answer[i] = low++;
            }
        }

        return answer;
    }
}
