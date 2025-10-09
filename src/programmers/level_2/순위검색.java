package programmers.level_2;

import java.util.*;

public class 순위검색 {

    public static void main(String[] args) {
        String[] info = {
                "java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"
        };

        String[] query = {
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"
        };

        int[] result = solution(info, query);

        for (int r : result) System.out.print(r + " ");
    }

    static Map<String, List<Integer>> map = new HashMap<>();
    public static int[] solution(String[] info, String[] query) {
        for (String i : info) {
            String[] arr = i.split(" ");
            String[] langs = {arr[0], "-"};
            String[] parts = {arr[1], "-"};
            String[] careers = {arr[2], "-"};
            String[] foods = {arr[3], "-"};
            int score = Integer.parseInt(arr[4]);

            for (String lang : langs)
                for (String part : parts)
                    for (String career : careers)
                        for (String food: foods) {
                            String key = String.join(" ", lang, part, career, food);
                            map.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
                        }
        }

        for (List<Integer> list : map.values()) Collections.sort(list);

        int[] answer = new int[query.length];
        int idx = 0;

        for (String q: query) {
            q = q.replaceAll(" and ", " ");
            String[] arr = q.split(" ");

            String key = String.join(" ", Arrays.copyOf(arr, 4));
            int targetScore = Integer.parseInt(arr[4]);

            if (!map.containsKey(key)) {
                answer[idx++] = 0;
                continue;
            }

            List<Integer> list = map.get(key);

            int left = 0;
            int right = list.size();

            while (left < right) {
                int mid = (left + right) / 2;

                if (list.get(mid) >= targetScore) right = mid;
                else left = mid + 1;
            }

            answer[idx++] = list.size() - left;
        }

        return answer;
    }
}
