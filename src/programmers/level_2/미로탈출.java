package programmers.level_2;

import java.util.LinkedList;
import java.util.Queue;

public class 미로탈출 {

    public static void main(String[] args) {
        String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
        System.out.println(solution(maps));
    }

    static int[] moveX = { -1, 1, 0, 0};
    static int[] moveY = { 0, 0, -1, 1};
    static boolean[][] visited;
    static String[][] stringMap;
    static int[][] countMap;
    static int maxX, maxY;
    public static int solution(String[] maps) {

        stringMap = new String[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];
        countMap = new int[maps.length][maps[0].length()];
        maxX = maps[0].length();
        maxY = maps.length;

        int startX = 0, startY = 0;
        int leverX = 0, leverY = 0;
        int exitX = 0, exitY = 0;

        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[0].length(); j++) {
                stringMap[i][j] = String.valueOf(maps[i].charAt(j));
                visited[i][j] = false;

                if(stringMap[i][j].equals("S")) {
                    startX = j;
                    startY = i;
                }
                if(stringMap[i][j].equals("L")) {
                    leverX = j;
                    leverY = i;
                }
                if(stringMap[i][j].equals("E")) {
                    exitX = j;
                    exitY = i;
                }
            }
        }

        BFS(startX, startY, leverX, leverY);

        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[0].length(); j++) {
                visited[i][j] = false;
            }
        }

        if(countMap[leverY][leverX] != 0) {
            BFS(leverX, leverY, exitX, exitY);
        } else {
            return -1;
        }

        if(countMap[exitY][exitX] != 0) {
            return countMap[exitY][exitX];
        }
        return -1;
    }

    private static void BFS(int startX, int startY, int destinationX, int destinationY) {
        boolean isArrive = false;
        visited[startY][startX] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        while(!queue.isEmpty()) {
            if(isArrive) break;
            int[] now = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nextX = now[0] + moveX[i];
                int nextY = now[1] + moveY[i];
                if(nextX >= 0 && nextY >= 0 && nextX < maxX && nextY < maxY && !visited[nextY][nextX]) {
                    if(!stringMap[nextY][nextX].equals("X")) {
                        visited[nextY][nextX] = true;
                        countMap[nextY][nextX] = countMap[now[1]][now[0]] + 1;
                        queue.add(new int[]{nextX, nextY});
                        if(nextX == destinationX && nextY == destinationY) {
                            isArrive = true;
                            break;
                        }
                    }
                }
            }
        }
    }
}
