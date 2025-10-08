package programmers.level_3;

import java.io.IOException;

public class 표현가능한이진트리 {

    public static void main(String[] args) throws IOException {
        long[] numbers = {7, 42, 5};
        int[] answer = solution(numbers);

        for (int a : answer) System.out.print(a + " ");
    }

    public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        int index = 0;
        for (long number : numbers) {
            String binaryNum = getBinaryNum(number);

            while (!isLength(binaryNum)) {
                binaryNum = "0" + binaryNum;
            }

            boolean check = checkTree(binaryNum);

            if (check) answer[index] = 1;
            index++;
        }

        return answer;
    }

    private static boolean checkTree(String binaryNum) {
        if (binaryNum.length() == 1) return true;

        int mid = binaryNum.length() / 2;
        char root = binaryNum.charAt(mid);

        String left = binaryNum.substring(0, mid);
        String right = binaryNum.substring(mid + 1);

        if (root == '0') {
            if (left.contains("1") || right.contains("1")) return false;
        }

        return checkTree(left) && checkTree(right);
    }

    private static String getBinaryNum(long number) {
        String binaryNum = "";

        while (number > 0) {
            if (number % 2 == 0) {
                binaryNum += "0";
            } else {
                binaryNum += "1";
            }

            number /= 2;
        }

        return new StringBuilder(binaryNum).reverse().toString();
    }

    private static boolean isLength(String binaryNum) {
        int length = binaryNum.length();
        int n = 2;

        while (true) {
            if (length < n) break;
            n *= 2;
        }

        if (n - 1 == length) return true;
        return false;
    }
}
