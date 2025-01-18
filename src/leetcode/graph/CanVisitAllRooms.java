package leetcode.graph;

import java.util.*;

public class CanVisitAllRooms {

    public static void main(String[] args) {
        List<List<Integer>> rooms = List.of(List.of(1), List.of(2), List.of(3), List.of());
        System.out.println(canVisitAllRooms(rooms));
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        visited.add(0);
        for (int key : rooms.get(0)) queue.add(key);

        while (!queue.isEmpty()) {
            int nowRoom = queue.poll();
            visited.add(nowRoom);

            for (int next : rooms.get(nowRoom)) {
                if (!visited.contains(next)) queue.add(next);
            }
        }

        return rooms.size() == visited.size();
    }
}
