package programmers.level_2;

import java.util.*;

public class 지게차와크레인 {

    public static void main(String[] args) {
        String[] storage = {
                "AZWQY",
                "CAABX",
                "BBDDA",
                "ACACA"
        };

        String[] requests = {"A", "BB", "A"};

//        String[] storage = {
//                "HAH",
//                "HBH",
//                "HHH",
//                "HAH",
//                "HBH"
//        };
//
//        String[] requests = {"C", "B", "B", "B", "B", "H"};
        System.out.println(solution(storage, requests));
    }

    public static int count = 0;
    static int[] moveX = {0, 0, -1, 1};
    static int[] moveY = {1, -1, 0, 0};
    public static int solution(String[] storage, String[] requests) {
        int total = storage.length * storage[0].length();

        String[][] map = new String[storage.length][storage[0].length()];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = storage[i].charAt(j) + "";
            }
        }

        for (String request : requests) {
            if (request.length() == 2) {
                removeCrane(map, request.substring(1));
            } else {
                removeCart(map, request);
            }
        }

        return total - count;
    }

    private static void removeCrane(String[][] map, String request) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals(request)) {
                    map[i][j] = "1";
                    count++;
                }
            }
        }
    }

    private static void removeCart(String[][] map, String request) {
        List<int[]> remove = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals(request)) {
                    if (isOutside(i,j,map) || canEscape(i,j, map)){
                        remove.add(new int[]{i,j});
                        count++;
                    }
                }
            }
        }

        for (int[] r : remove) {
            map[r[0]][r[1]] = "1";
        }
    }

    private static boolean isOutside(int i, int j, String[][] map) {
        if (i == 0 || i == map.length -1 || j == 0 || j == map[0].length - 1) return true;
        return false;
    }

    private static boolean canEscape(int x, int y, String[][] map) {
        Boolean[][] visited = new Boolean[map.length][map[0].length];
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int nowX = point[0];
            int nowY = point[1];
            visited[nowX][nowY] = true;
            if (isOutside(nowX, nowY, map)) return true;

            for (int k = 0; k < 4; k++) {
                int nextX = nowX + moveX[k];
                int nextY = nowY + moveY[k];

                if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length) continue;
                if (visited[nextX][nextY]) continue;

                if (map[nextX][nextY].equals("1")) {
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
        return false;
    }
}
