package leetcode.dp;

public class longestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("s"));
    }

    public static String longestPalindrome(String s) {
        int size = s.length();

        if(size == 1) return s;

        int maxlen = 1;
        String maxStr = s.substring(0, 1);

        for (int i = 0; i < size; i++) {
            for (int j = maxlen + 1; j <= size; j++) {
                if(j-i > maxlen && isPalindrome(s.substring(i, j))) {
                    maxlen = j-i;
                    maxStr = s.substring(i, j);
                }
            }
        }

        return maxStr;
    }

    private static boolean isPalindrome(String word) {
        int size = word.length();
        for(int i = 0; i < size / 2; i++) {
            if(word.charAt(i) != word.charAt(size - i - 1)) return false;
        }
        return true;
    }
}
