package leetcode.backtracking;

public class NextBeautifulNumber {

    public static void main(String[] args) {
        System.out.println(nextBeautifulNumber(1));
    }

    public static int nextBeautifulNumber(int n) {
        for (int i = n + 1; i < Integer.MAX_VALUE; i++) {
            if (isBalanced(i)) return i;
        }
        return 0;
    }

    private static boolean isBalanced(int n) {
        String strNum = String.valueOf(n);

        int[] balance = new int[10];
        for (char c : strNum.toCharArray()) {
            balance[c - '0']++;
        }

        for (int i = 0; i < 10; i++) {
            if (balance[i] == 0) continue;
            if (balance[i] != i) return false;
        }

        return true;
    }
}
