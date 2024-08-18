package programmers.깊이너비우선탐색;

import java.util.*;

public class ex6_여행경로 {
    public static void main(String[] args) {

        String[][] tickets = {
                {"ICN", "BBB"}, {"BBB", "ICN"}, {"ICN", "AAA"}
        };

        String[] result = solution(tickets);

        for(String s : result){
            System.out.println(s);
        }

    }

    public static String[] solution(String[][] tickets) {
        ArrayList<String> result = new ArrayList<>();

        HashMap<String, Boolean[]> visitedMap = new HashMap<>();
        HashMap<String, ArrayList<String>> pathMap = new HashMap<>();      // [출발: 도착리스트]

        for(String[] ticket : tickets){
            String departure = ticket[0];
            String arrival = ticket[1];
            ArrayList<String> arrivals = pathMap.getOrDefault(departure, new ArrayList<>());
            arrivals.add(arrival);
            pathMap.put(departure, arrivals);
        }

        for(String key : pathMap.keySet()){
            Boolean[] visited = new Boolean[pathMap.get(key).size()];
            Arrays.fill(visited, false);
            visitedMap.put(key, visited);
        }

        for(ArrayList<String> arrivals : pathMap.values()){
            Collections.sort(arrivals);
        }

        Queue<String> queue = new LinkedList<>();
        queue.add("ICN");
        result.add("ICN");

        while (result.size() <= tickets.length){
            String nowDeparture = queue.poll();

            // 끊겼을 때 경우 대비


            for(int i = 0; i < pathMap.get(nowArrival).size(); i++){
                if(!visitedMap.get(nowArrival)[i]){
                    queue.add(pathMap.get(nowArrival).get(i));
                    result.add(pathMap.get(nowArrival).get(i));
                    visitedMap.get(nowArrival)[i] = true;
                    break;
                }
            }
        }

        return result.toArray(new String[0]);

    }
}
