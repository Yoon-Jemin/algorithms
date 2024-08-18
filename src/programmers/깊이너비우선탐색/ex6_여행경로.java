package programmers.깊이너비우선탐색;

import java.util.*;

public class ex6_여행경로 {
    public static void main(String[] args) {

//        String[][] tickets = {
//                {"ICN", "BOO"}, // 1
//                {"ICN", "COO"}, // 5
//                {"BOO", "DOO"}, // 2
//                {"BOO", "ICN"}, // 4
//                {"COO", "BOO"},
//                {"COO", "DOO"}, // 6 (핵심)
//                {"DOO", "BOO"}, // 3
//                {"DOO", "COO"}
//        };

        String[][] tickets = {
                {"ICN", "A"}, // 1
                {"A", "B"},  // 2
                {"A", "C"},  //
                {"B", "D"},  // 3
                {"C", "A"}
        };

        String[] result = solution(tickets);

        for(String s : result){
            System.out.println(s);
        }
    }

    static boolean[] visit;
    static ArrayList<String> list;
    public static String[] solution(String[][] tickets) {
        visit = new boolean[tickets.length];
        list = new ArrayList<>();
        DFS(0, "ICN", "ICN", tickets);
        Collections.sort(list);
        String[] temp = list.get(0).split(" ");
        return temp;
    }

    private static void DFS(int cnt, String icn, String next, String[][] tickets) {
        if(cnt == tickets.length){
            list.add(next);
        } else {
            for (int i = 0; i < tickets.length; i++){
                if(!visit[i] && tickets[i][0].equals(icn)){ // i번째 티켓의 출발지가 현재 도착지
                    visit[i] = true;
                    DFS(cnt+1, tickets[i][1], next+" "+tickets[i][1], tickets);
                    visit[i] = false;
                }
            }
        }

    }


}
