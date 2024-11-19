package programmers.level_3;

import java.util.*;

public class 셔틀버스 {

    public static void main(String[] args) {
        System.out.println(solution(2, 10, 2, new String[]{"09:10", "09:09", "08:00"}));
    }

    public static String solution(int n, int t, int m, String[] timetable) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (String time : timetable) {
            queue.add(ParseToInt(time));
        }

        int busTime = 540;
        int result = 0;

        for (int i = 0; i < n; i++) {
            int count = 0;
            int lastPersonTime = 0;

            while (count < m && !queue.isEmpty() && queue.peek() <= busTime) {
                lastPersonTime = queue.poll();
                count++;
            }

            if (i == n -1){
                result = (count == m) ? lastPersonTime - 1 : busTime;
            }

            busTime += t;
        }

        return ParseToString(result);
    }


    private static Integer ParseToInt(String time) {
        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return hour * 60 + minute;
    }

    private static String ParseToString(int result) {
        int hour = result / 60;
        int minute = result % 60;
        StringBuilder sb = new StringBuilder();
        if(hour < 10) sb.append("0").append(hour);
        else sb.append(hour);

        sb.append(":");

        if(minute < 10) sb.append("0").append(minute);
        else sb.append(minute);

        return sb.toString();
    }
}
