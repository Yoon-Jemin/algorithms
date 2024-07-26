package programmers.level_2;

public class 두원사이의정수쌍 {

    public static void main(String[] args) {

        System.out.println(solution(2,3));

    }

    public static long solution(int r1, int r2) {
        long answer = 0;

        long r1Square = (long) r1* r1;
        long r2Square = (long) r2* r2;
        long maxX = 0;
        long minX = 0;

        for(long y = r2; y > 0; y--){
            maxX = (long) Math.sqrt(r2Square - y*y);
            minX = (y >= r1) ? 0 : (long) Math.sqrt(r1Square - y*y);

            if(minX*minX + y*y < r1Square) minX++;

            answer += maxX - minX + 1;
        }

        return answer* 4L;
    }
}