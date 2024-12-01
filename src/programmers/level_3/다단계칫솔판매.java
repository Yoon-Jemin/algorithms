package programmers.level_3;

import java.util.*;

public class 다단계칫솔판매 {

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"sam", "emily", "jaimie", "edward"};
        int[] amount = {2, 3, 5, 4};
        int[] answer = solution(enroll, referral, seller, amount);
        System.out.println(Arrays.toString(answer));
    }

    static int[] profit;
    static List<String>[] graph;
    static Map<String, Integer> map;
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        profit = new int[enroll.length];
        graph = new ArrayList[enroll.length];
        map = new HashMap<>();     // 이름 : 인덱스

        for (int i = 0; i < enroll.length; i++) {
            graph[i] = new ArrayList<>();
            map.put(enroll[i], i);
        }

        for (int i = 0; i < referral.length; i++) {
            if (referral[i] == "-") continue;
            graph[map.get(enroll[i])].add(referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            DFS(seller[i], amount[i] * 100);
        }

        return profit;
    }

    private static void DFS(String seller, int price) {
        if (!map.containsKey(seller)) return;

        if (price >= 10){
            int give = (price / 10) ;
            int mine = price - give;
            profit[map.get(seller)] += mine;
            if(!graph[map.get(seller)].isEmpty()){
                DFS(graph[map.get(seller)].get(0), give);
            }
        } else {
            profit[map.get(seller)] += price;
        }
    }
}
