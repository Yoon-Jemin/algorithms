package programmers.level_2;

public class 이진변환하기 {

    public static void main(String[] args) {
        int[] answer = solution("110010101001");
        System.out.println(answer[0]);
        System.out.println(answer[1]);
    }

    public static int[] solution(String s) {
        int[] answer = new int[2];

        int count = 0;
        int zero = 0;
        while (!s.equals("1")) {
            int zeroCount = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') zeroCount++;
            }

            int nextNum = s.length() - zeroCount;
            String next = toBinaryString(nextNum);

            zero += zeroCount;
            count++;
            s = next;
        }

        answer[0] = count;
        answer[1] = zero;

        return answer;
    }


    public static String toBinaryString(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            sb.append(n % 2);
            n = n / 2;
        }
        return sb.toString();
    }
}
