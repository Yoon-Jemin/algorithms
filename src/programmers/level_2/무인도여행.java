package programmers.level_2;

import java.util.*;

public class 무인도여행 {

    public static void main(String[] args) {
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
        int[] answer = solution(maps);
        for (int i : answer) System.out.println(i);
    }

//    "X 5 9 1 X"     "0 5 9 1 0"
//    "X 1 X 5 X"     "0 1 0 5 0"
//    "X 2 3 1 X"     "0 2 3 1 0"
//    "1 X X X 1"     "1 0 0 0 1"

    static int[] moveX = { -1, 1, 0, 0};
    static int[] moveY = { 0, 0, -1, 1};
    static int maxX, maxY;
    public static int[] solution(String[] maps) {

        maxX = maps[0].length();
        maxY = maps.length;

        Integer[][] integerMap = new Integer[maps.length][maps[0].length()];
        Boolean[][] visitedMap = new Boolean[maps.length][maps[0].length()];
        Integer[][] indexMap = new Integer[maps.length][maps[0].length()];

        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[i].length(); j++) {
                if(maps[i].charAt(j) == 'X') {
                    integerMap[i][j] = 0;
                } else{
                    integerMap[i][j] = maps[i].charAt(j) - '0';
                }
                visitedMap[i][j] = false;
                indexMap[i][j] = 0;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        int index = 1;

        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[i].length(); j++) {
                int nowX = j;
                int nowY = i;

                if(integerMap[i][j] != 0 && !visitedMap[i][j]){
                    visitedMap[nowY][nowX] = true;
                    indexMap[nowY][nowX] = index;
                    map.put(index, integerMap[nowY][nowX]);

                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{nowX, nowY});
                    while(!queue.isEmpty()) {
                        int[] now = queue.poll();
                        for(int k = 0; k < 4; k++){
                            int nextX = now[0] + moveX[k];
                            int nextY = now[1] + moveY[k];
                            if(nextX >= 0 && nextX < maxX && nextY >= 0 && nextY < maxY){
                                if(!visitedMap[nextY][nextX] && integerMap[nextY][nextX] != 0){
                                    visitedMap[nextY][nextX] = true;
                                    indexMap[nextY][nextX] = indexMap[now[1]][now[0]];
                                    map.put(index, map.get(index) + integerMap[nextY][nextX]);
                                    queue.add(new int[]{nextX, nextY});
                                }
                            }
                        }
                    }
                    index++;
                }
            }
        }

        if(map.isEmpty()) return new int[]{-1};

        int[] answer = new int[map.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = map.get(i+1);
        }

        Arrays.sort(answer);

        return answer;
    }
}
