package programmers.level_1;

public class 저주받은마을 {
    public static void main(String[] args) {

        System.out.println(solution(15));
    }

    static int answer;
    public static int solution(int n) {
        answer = 1;

        for (int i = 1; i < n; i++){
            answer++;
            while(!check(answer)){
                answer++;
            }
        }

        return answer;
    }

    private static boolean check(int num) {
        if(num % 3 == 0){
            return false;
        } else {
            String strNum = String.valueOf(num);
            for(char c : strNum.toCharArray()){
                if(c == '3'){
                    return false;
                }
            }
        }
        return true;
    }
}
