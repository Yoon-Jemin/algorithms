package baekjoon.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 연구소 {

    static int N, M;
    static int[][] map;
    static int[] moveX = {0, 0, 1, -1};
    static int[] moveY = {1, -1, 0, 0};
    static List<Point> empty = new ArrayList<>();
    static List<Point> virus = new ArrayList<>();
    static int maxSafeArea = 0;

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    empty.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    virus.add(new Point(i, j));
                }
            }
        }

        setWallsAndSimulate();
        System.out.println(maxSafeArea);
    }

    private static void setWallsAndSimulate() {
        int size = empty.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    int[][] copiedMap = copyMap(map);

                    Point p1 = empty.get(i);
                    Point p2 = empty.get(j);
                    Point p3 = empty.get(k);

                    copiedMap[p1.x][p1.y] = 1;
                    copiedMap[p2.x][p2.y] = 1;
                    copiedMap[p3.x][p3.y] = 1;

                    spreadVirus(copiedMap);
                    maxSafeArea = Math.max(maxSafeArea, countSafeArea(copiedMap));
                }
            }
        }
    }

    private static int[][] copyMap(int[][] original) {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[i][j] = original[i][j];
            }
        }
        return newMap;
    }

    private static void spreadVirus(int[][] map) {
        Queue<Point> queue = new LinkedList<>();
        for (Point v : virus) {
            queue.offer(new Point(v.x, v.y));
        }

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + moveX[i];
                int nextY = cur.y + moveY[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
                if (map[nextX][nextY] == 0) {
                    map[nextX][nextY] = 2;
                    queue.offer(new Point(nextX, nextY));
                }
            }
        }
    }

    private static int countSafeArea(int[][] map) {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) count++;
            }
        }

        return count;
    }
}
