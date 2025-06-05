package programmers.level_2;

public class 다음큰숫자 {

    public static void main(String[] args) {
        int n = 78;
        System.out.println(solution(n));
    }

    public static int solution(int n) {

        String binaryNum = Integer.toBinaryString(n);
        int target = numberOfOnes(binaryNum);

        int num = n + 1;
        while (true) {
            if (numberOfOnes(Integer.toBinaryString(num)) == target) {
                return num;
            }
            num++;
        }
    }

    private static int numberOfOnes(String binaryNum) {
        int answer = 0;
        for (int i = 0; i < binaryNum.length(); i++) {
            if (binaryNum.charAt(i) == '1') answer++;
        }

        return answer;
    }
}
