package programmers.해시;

import java.util.*;
import java.util.stream.Collectors;

public class ex5_베스트앨범 {

    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Long> entire = new HashMap<>();     // 장르에 대한 전체 재생 횟수
        HashMap<String, ArrayList<int[]>> memo = new HashMap<>();   // 키: 노래 장르, ArrayList: int[0] = 고유 번호, int[1] = 재생 횟수
        ArrayList<Integer> list = new ArrayList<>();    // 정답

        int size = plays.length;
        for(int i = 0; i < size; i++){
            String name = genres[i];
            int play = plays[i];
            entire.put(name, entire.getOrDefault(name, 0L) + play);
            if(memo.containsKey(name)){
                memo.get(name).add(new int [] {i, play});
            }
            else{
                ArrayList<int []> temp = new ArrayList<>();
                temp.add(new int [] {i, play});
                memo.put(name, temp);
            }
        }

        // 재생 횟수를 기준으로 장르 정렬 (내림차순)
        List<Map.Entry<String, Long>> collect = entire.entrySet().stream().sorted((o1, o2) -> {
            return -(o1.getValue().compareTo(o2.getValue()));
        }).collect(Collectors.toList());

        // 장르 별로 가장 플레이 횟수가 많은 고유 번호 꺼내기
        for(int i = 0; i < entire.size(); i++){
            Map.Entry<String, Long> entry = collect.get(i);
            ArrayList<int[]> temp = memo.get(entry.getKey());
            Collections.sort(temp, (o1, o2) -> {
                return -(o1[1] - o2[1]);
            });
            for(int j = 0; j < temp.size(); j++){
                if(j == 2) break;
                int [] check = temp.get(j);
                list.add(check[0]);
            }
        }

        // 정답을 list -> array
        int[] answer = new int [list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }

        return answer;
    }
}
