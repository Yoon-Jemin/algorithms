package programmers.동적계획법;

public class 등굣길 {

    public static void main(String[] args) {

        int[][] puddles = {{2,2}};
        System.out.println(solution(4,3,puddles));

    }

    /* DP */
    public static int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n+2][m+2];
        int limit = 1000000007;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <=m; j++){
                map[i][j] = 1;
            }
        }

        for(int[] puddle : puddles){
            map[puddle[1]][puddle[0]] = 0;
            if(puddle[1] == 1){     // [2,1]: 첫번째 행에 0
                for(int i = puddle[0] + 1; i <= m; i++) map[puddle[1]][i] = 0;
            }
            if(puddle[0] == 1){     // [1,2]: 첫번째 열에 0
                for(int i = puddle[1] + 1; i <= n; i++) map[i][puddle[0]] = 0;
            }
        }

        for(int y = 2; y <= n; y++){
            for(int x = 1; x <= m; x++){
                if(map[y][x] != 0) map[y][x] = map[y-1][x] + map[y][x-1];
                map[y][x] %= limit;
            }
        }

        return map[n][m];
    }


    /* 재귀 */
//    static int[][] map;
//    static int finalX;
//    static int finalY;
//    static int answer;
//    public static int solution(int m, int n, int[][] puddles) {
//        answer = 0;
//
//        map = new int[n+2][m+2];
//        finalX = m;
//        finalY = n;
//
//        for(int i = 1; i <= n; i++){
//            for(int j = 1; j <=m; j++){
//                map[i][j] = 1;
//            }
//        }
//
//        for(int[] puddle : puddles){
//            map[puddle[1]][puddle[0]] = 0;
//        }
//
//        BFS(1, 1);
//
//        return answer;
//    }
//
//    private static void BFS(int x, int y) {
//
//        if(x == finalX && y == finalY) answer++;
//
//        if(map[y][x+1] != 0) BFS(x+1, y);
//        if(map[y+1][x] != 0) BFS(x, y+1);
//    }
}
