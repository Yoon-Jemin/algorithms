package leetcode.string;

public class LongestContinuousSubstring {

    public static void main(String[] args) {
        System.out.println(longestContinuousSubstring("abcde"));
    }

    public static int longestContinuousSubstring(String s) {
        int longest = 0;

        for (int i = 0; i < s.length(); i++) {
            int now = s.charAt(i) - '0';
            int length = 1;
            while (i < s.length() - 1 && s.charAt(i + 1) - '0' == now + 1) {
                i++;
                length++;
                now = s.charAt(i) - '0';
            }
            longest = Math.max(longest, length);
        }

        return longest;
    }
}
