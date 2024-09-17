package programmers.level_2;

import java.util.*;

public class 호텔대실 {

    public static void main(String[] args) {

        String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        System.out.println(solution(book_time));
    }

    public static int solution(String[][] bookTimes) {

        PriorityQueue<Integer[]> roomQueue = new PriorityQueue<>(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        for (String[] bookTime : bookTimes) {
            String[] startParts = bookTime[0].split(":");
            int startHour = Integer.parseInt(startParts[0]);
            int startMinute = Integer.parseInt(startParts[1]);
            int startTime = startHour * 60 + startMinute;

            String[] endParts = bookTime[1].split(":");
            int endHour = Integer.parseInt(endParts[0]);
            int endMinute = Integer.parseInt(endParts[1]);
            int endTime = endHour * 60 + endMinute + 10;

            roomQueue.add(new Integer[]{startTime, endTime});
        }

        List<Integer> exitTimeList = new ArrayList<>();

        while (!roomQueue.isEmpty()) {
            Integer[] room = roomQueue.poll();
            if(exitTimeList.isEmpty()) {
                exitTimeList.add(room[1]);
            } else {
                boolean isAdd = false;
                for (Integer exitTime : exitTimeList) {
                    if(exitTime <= room[0]){
                        exitTimeList.remove(exitTime);
                        exitTimeList.add(room[1]);
                        isAdd = true;
                        break;
                    }
                }
                if(!isAdd) {
                    exitTimeList.add(room[1]);
                }
            }
        }

        return exitTimeList.size();

    }

}
