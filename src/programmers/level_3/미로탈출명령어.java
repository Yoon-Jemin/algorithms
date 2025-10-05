package programmers.level_3;


public class 미로탈출명령어 {

    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        int x = 2;
        int y = 3;
        int r = 3;
        int c = 1;
        int k = 5;

        System.out.println(solution(n, m, x, y, r, c, k));
    }

    static String answer = "";
    static char[] dir = {'d', 'l', 'r', 'u'};
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static int N, M,endX ,endY, distance;
    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        endX = r;
        endY = c;
        distance = k;

        int manhattan = Math.abs(x - r) + Math.abs(y - c);
        if (manhattan > distance || (distance - manhattan) % 2 != 0) return "impossible";

        DFS(x, y, "");

        return answer.equals("") ? "impossible" : answer;
    }

    public static void DFS(int nowX, int nowY, String nowPath) {
        if (answer.length() == distance) return;

        if (nowPath.length() >= distance) {
            if (nowX == endX && nowY == endY) {     // 목표에 도달함
                answer = nowPath;
            }
            return;
        }

        int remain = distance - nowPath.length();
        int manhattan = Math.abs(nowX - endX) + Math.abs(nowY - endY);
        if (manhattan > remain) return;
        if ((remain - manhattan) % 2 != 0) return;

        for (int i = 0; i < 4; i++) {
            int nextX = nowX + dx[i];
            int nextY = nowY + dy[i];

            if (nextX <= 0 || nextX > N  || nextY <= 0 || nextY > M) continue;

            String newPath = nowPath + dir[i];
            DFS(nextX, nextY, newPath);
        }
    }
}
