package programmers.level_2;

public class 마법의엘리베이터 {

    public static void main(String[] args) {
        System.out.println(solution(485));
    }

    public static int solution(int storey) {
        int answer = 0;

        String strNum = String.valueOf(storey);

        for(int i = 1; i <= strNum.length(); i++) {
            int temp1 = storey % 10;

            if(temp1 > 5){
                storey += (10 - temp1);
                answer += (10 - temp1);
            } else if(temp1 < 5){
                storey -= temp1;
                answer += temp1;
            } else {
                int temp2 = (storey / 10) % 10;
                if(temp2 > 4){
                    storey += (10 - temp1);
                    answer += (10 - temp1);
                } else {
                    storey -= temp1;
                    answer += temp1;
                }
            }

            storey /= 10;
        }

        if(storey == 1) answer++;

        return answer;
    }
}
