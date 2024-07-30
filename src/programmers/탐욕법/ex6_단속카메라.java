package programmers.탐욕법;

import java.util.*;

public class ex6_단속카메라 {
    public static void main(String[] args) {

//        int[][] routes = {{-20,-15}, {-14,-5}, {-18, -13}, {-5, -3}};
//        int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};
        int[][] routes = {{-20,15}, {-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        System.out.println(solution(routes));

    }

    public static int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
                else return Integer.compare(o1[1], o2[1]);
            }
        });

        Queue<int[]> routeQueue = new LinkedList<>();
        Collections.addAll(routeQueue, routes);

        while (!routeQueue.isEmpty()){
            int[] nowRoute = routeQueue.poll();
            if(routeQueue.isEmpty()) {
                answer++;
                break;
            }
            int limit = nowRoute[1];

            while(!routeQueue.isEmpty() && routeQueue.peek()[0] <= limit){
                if(routeQueue.peek()[1] <= limit) {
                    limit = routeQueue.peek()[1];
                }
                routeQueue.poll();
            }

            answer++;
        }

        return answer;
    }
}
