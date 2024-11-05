package programmers.level_3;

public class 최고의집합 {

    public static void main(String[] args) {
        int[] result = solution(2, 9);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }

    public static int[] solution(int n, int s) {
        if(n > s) return new int[] {-1};

        int[] answer = new int[n];

        int quotient = s / n;
        int remainder = s % n;

        for (int i = 0; i < n; i++) {
            answer[i] = quotient;
        }

        int idx = n-1;
        while (remainder > 0) {
            answer[idx]++;
            idx--;
            remainder--;
        }

        return answer;
    }
}
