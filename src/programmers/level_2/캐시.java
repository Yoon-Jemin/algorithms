package programmers.level_2;

import java.util.*;

public class 캐시 {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if (cacheSize == 0) return cities.length * 5;

        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();

        for (String city : cities) {
            city = city.toLowerCase();
            if (!set.contains(city)) {  // 캐시 미스
                if (set.size() >= cacheSize) {   // 캐시 full
                    set.remove(queue.poll());
                    set.add(city);
                    queue.offer(city);
                } else {    // 캐시 idle
                    set.add(city);
                    queue.offer(city);
                }
                answer += 5;
            } else {    // 캐시 히트
                Queue<String> newQueue = new LinkedList<>();
                while (!queue.isEmpty()) {
                    if (queue.peek().equals(city)) {
                        queue.poll();
                        continue;
                    }
                    newQueue.add(queue.poll());
                }
                newQueue.add(city);
                queue = newQueue;
                answer += 1;
            }
        }

        return answer;
    }
}
