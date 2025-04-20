package programmers.level_3;

public class 가장긴팰린드롬 {

    public static void main(String[] args) {
        String s = "abcdcba";
        System.out.println(solution(s));
    }

    public static int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(i, i, s);
            int len2 = expand(i, i + 1, s);
            answer = Math.max(answer, Math.max(len1, len2));
        }
        return answer;
    }

    private static int expand(int left, int right, String s) {
        while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
