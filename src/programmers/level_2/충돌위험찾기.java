package programmers.level_2;

import java.util.*;

public class 충돌위험찾기 {

    public static void main(String[] args) {
        System.out.println(solution(
                new int[][]{{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}},    // points
                new int[][]{{2, 3, 4, 5}, {1, 3, 4, 5}}     // routes
        ));
    }

    static int [][] POINTS;
    static int [][] ROUTES;
    public static int solution(int[][] points, int[][] routes) {
        POINTS = points;
        ROUTES = routes;
        int answer = 0;
        int numRobots = routes.length;
        ArrayList<Robot> robots = new ArrayList<>();

        // 초기화
        for (int[] route : routes) {
            if(route.length == 2){  // 경로가 2개일 때
                int[] start = points[route[0]-1];
                int[] end = points[route[1]-1];
                robots.add(new Robot(start, end));
            } else {    // 경로가 여러 개일 때
                int[] start = points[route[0]-1];
                int[] end = points[route[1]-1];
                int[] nextPoints = Arrays.copyOfRange(route, 2, route.length);
                robots.add(new Robot(start, end, nextPoints));
            }
        }

        while(numRobots > 0) {
            Map<int[], Integer> locations = new HashMap<>();

            for(Robot robot : robots) {
                int[] now = new int[]{robot.now[0], robot.now[1]};
                if(locations.isEmpty()) {
                    locations.put(now, 1);
                } else {
                    boolean isAdd = true;
                    for(int[] location : locations.keySet()) {
                        if(now[0] == location[0] && now[1] == location[1]) {
                            locations.put(location, locations.get(location) + 1);
                            isAdd = false;
                            break;
                        }
                    }
                    if(isAdd) locations.put(now, 1);
                }
            }

            for(Integer num : locations.values()) {
                if(num >= 2) answer++;
            }

            // 도착했는지 검사
            Iterator<Robot> iterator = robots.iterator();
            while(iterator.hasNext()) {
                Robot robot = iterator.next();
                if(robot.isArrive()){
                    if(!robot.isEnd()){
                        robot.setNextDestination();
                    } else {
                        iterator.remove();
                        numRobots--;
                    }
                }
            }

            // 다음으로 이동
            for(Robot robot : robots) {
                robot.moveNext();
            }
        }

        return answer;
    }

    public static class Robot{

        public int[] now;
        public int[] destination;
        public int[] nextPoints;    // 4, 5
        public int index = 0;

        public Robot(int[] now, int[] destination) {
            this.now = now.clone();
            this.destination = destination.clone();
            this.nextPoints = null;
        }

        public Robot(int[] now, int[] destination, int[] nextPoints) {
            this.now = now.clone();
            this.destination = destination.clone();
            this.nextPoints = nextPoints.clone();
        }

        public void moveNext() {
            if(now[0] != destination[0]) {  // 세로축 일치시키기
                if(now[0] > destination[0]) {
                    this.now[0]--;
                } else {
                    this.now[0]++;
                }
            } else {    // 가로축 일치시키기
                if(now[1] > destination[1]) {
                    this.now[1]--;
                } else {
                    this.now[1]++;
                }
            }
        }

        public boolean isArrive() {
            if(now[0] == destination[0] && now[1] == destination[1]) return true;
            return false;
        }

        public boolean isEnd() {
            if(nextPoints == null) return true;
            if(index == nextPoints.length) return true;
            return false;
        }

        public void setNextDestination() {
            destination = POINTS[nextPoints[index]-1];
            index++;
        }
    }
}
