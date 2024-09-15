package programmers.level_2;

public class 당구연습 {

    public static void main(String[] args) {
        int[][] balls = {{7, 7}, {2, 7}, {7, 3}};
        int[] answer = solution(10, 10, 3, 7, balls);
        for(int i : answer) System.out.println(i);

    }

    public static int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        int nowX = startX;;
        int nowY = startY;

        for(int i = 0; i < answer.length; i++) {
            int nextX = balls[i][0];
            int nextY = balls[i][1];
            int distance = 0;

            // 1. 왼쪽 가로벽을 맞는 경우
            int distance1 = (nowY - nextY) * (nowY - nextY) + (nowX + nextX) * (nowX + nextX);
            // 2. 오른쪽 가로벽을 맞는 경우
            int distance2 = (nowY - nextY) * (nowY - nextY) + (m - nowX + m - nextX) * (m - nowX + m - nextX);
            // 3. 아래쪽 세로벽을 맞는 경우
            int distance3 = (nowX - nextX) * (nowX - nextX) + (nowY + nextY) * (nowY + nextY);
            // 4. 위쪽 세로벽을 맞는 경우
            int distance4 = (nowX - nextX) * (nowX - nextX) + (n - nowY + n - nextY) * (n - nowY + n - nextY);

            if(startX == nextX) {
                if(nowY < nextY){
                    distance4 = Integer.MAX_VALUE;
                } else {
                    distance3 = Integer.MAX_VALUE;
                }
            }

            if(startY == nextY) {
                if(nowX < nextX) {
                    distance2 = Integer.MAX_VALUE;
                } else {
                    distance1 = Integer.MAX_VALUE;
                }
            }

            distance = Math.min(distance1, Math.min(distance2, Math.min(distance3, distance4)));
            answer[i] = distance;
        }

        return answer;
    }
}
