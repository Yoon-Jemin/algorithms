package programmers.level_2;

public class N진수게임 {

    public static void main(String[] args) {
        System.out.println(solution(2,4,2,1));
    }

    public static String solution(int n, int t, int m, int p) {
        String answer = "";
        String strNum = "";

        int number = 0;
        while(strNum.length() <= t * m) {
            strNum += nextNum(n, number);
            number++;
        }

        for(int i = 0; i < strNum.length(); i++) {
            if(i % m == p-1 ){    // 튜브 차례
                answer += strNum.charAt(i);
            }
            if(answer.length() == t) break;
        }

        return answer;
    }

    private static String nextNum(int n, int number) {
        String answer = "";

        if(number == 0) return "0";

        int result = number;
        while(result != 0){
            int remainder = result % n;    // 나머지
            if(remainder < 10){
                answer = remainder + answer;
            } else {
                if( remainder == 10) answer = 'A' + answer;
                else if(remainder == 11) answer = 'B' + answer;
                else if(remainder == 12) answer = 'C' + answer;
                else if(remainder == 13) answer = 'D' + answer;
                else if(remainder == 14) answer = 'E' + answer;
                else if(remainder == 15) answer = 'F' + answer;
            }
            result = result / n;
        }

        return answer;
    }
}
