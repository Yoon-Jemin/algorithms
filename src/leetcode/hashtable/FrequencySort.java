package leetcode.hashtable;

import java.util.*;

public class FrequencySort {

    public static void main(String[] args) {
        System.out.println(frequencySort("Aabb"));
    }

    public static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>((a,b) -> {
            if (map.get(a) != map.get(b)) {
                return map.get(b) - map.get(a);
            }
            return Character.compare(a, b);
        });

        pq.addAll(map.keySet());

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Character c = pq.poll();
            sb.append(String.valueOf(c).repeat(map.get(c)));
        }

        return sb.toString();
    }


}
