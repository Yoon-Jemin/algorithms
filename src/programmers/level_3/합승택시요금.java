package programmers.level_3;

import java.util.ArrayList;

public class 합승택시요금 {

    public static void main(String[] args) {
        System.out.println(solution(6, 4, 6, 2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
    }

    static int answer = 200 * 100000;
    static ArrayList<int[]>[] graph;
    public static int solution(int n, int s, int a, int b, int[][] fares) {

        // 1. 배열을 선언하고 초기화
        int[][] map = new int[n+1][n+1];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= n; j++){
                if(i == j) continue;
                else{
                    map[i][j] = answer;
                }
            }
        }

        // 2. 최단 거리 배열에 그래프 데이터 저장
        for(int i = 0; i < fares.length; i++){
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];
            map[start][end] = cost;
            map[end][start] = cost;
        }

        // 3. 점화식으로 배열 업데이트
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(map[i][j] > map[i][k] + map[k][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        // 각자 따로 가는 경우
        answer = Math.min(answer, map[s][a] + map[s][b]);
        for(int i = 1; i <= n; i++){
            answer = Math.min(answer, map[s][i] + map[i][a] + map[i][b]);
        }

        return answer;
    }
}
