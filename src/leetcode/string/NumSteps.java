package leetcode.string;

public class NumSteps {

    public static void main(String[] args) {
        System.out.println(numSteps("1101"));
    }

    public static int numSteps(String s) {
        int count = 0;

        while (!s.equals("1")) {
            String last = s.substring(s.length()-1);

            if (last.equals("0")) { // 짝수
                s = s.substring(0, s.length()-1);
            } else {    // 홀수
                String nexString = "";
                int carry = 1;
                for (int i = s.length()-1; i >= 0; i--) {
                    int num = s.charAt(i) - '0';
                    if (carry + num == 2) {
                        carry = 1;
                        nexString = "0" + nexString;
                    } else if (carry + num == 1) {
                        carry = 0;
                        nexString = "1" + nexString;
                    } else if (carry + num == 0) {
                        carry = 0;
                        nexString = "0" + nexString;
                    }
                }
                if (carry == 1) nexString = "1" + nexString;
                s = nexString;
            }
            count++;
        }

        return count;
    }
}
