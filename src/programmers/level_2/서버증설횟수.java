package programmers.level_2;

public class 서버증설횟수 {

    public static void main(String[] args) {
        int[] players = {0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5};
        int m = 3;
        int k = 5;
        System.out.println(solution(players, m, k));
    }

    public static int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] server = new int[players.length];

        for (int i = 0; i < players.length; i++) {
            if (players[i] < (server[i] + 1) * m) continue;

            int addServer = 0;
            while (players[i] >= (server[i] + 1 + addServer) * m) {
                addServer++;
            }

            for (int j = i; j < i + k; j++) {
                if (j > players.length - 1) break;
                server[j] += addServer;
            }

            answer += addServer;
        }


        return answer;
    }
}
