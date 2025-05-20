package leetcode.string;

public class CountAndSay {

    public static void main(String[] args) {
        System.out.println(countAndSay(6));
    }

    public static String countAndSay(int n) {
        String num = "1";
        for (int i = 1; i < n; i++) {
            num = method(num);
        }

        return num;
    }

    private static String method(String num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            int count = 1;
            while (i + 1 < num.length() && c == num.charAt(i + 1)) {
                count++;
                i++;
            }
            sb.append(count).append(c);
        }

        return sb.toString();
    }
}
