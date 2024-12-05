package programmers.level_3;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class 추석트래픽 {

    public static void main(String[] args) {
        String[] lines = new String[] {
                "2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        };

        System.out.println(solution(lines));
    }

    public static class Response{
        String date;
        String startTime;
        String endTime;

        public Response(String date, String startTime, String endTime) {
            this.date = date;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static int solution(String[] lines) {
        List<Response> responses = new ArrayList<>();
        for (String line : lines) {
            responses.add(parse(line));
        }

        PriorityQueue<Integer> maxCount = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < responses.size(); i++) {
            String startTime = responses.get(i).startTime;

            int count = 0;
            int startSecond = Integer.parseInt(startTime.substring(6, 8));
            int currentTime = changeTimeToInteger(startTime);

            for (int j = 0; j < responses.size(); j++) {
                int nextStartSecond = Integer.parseInt(responses.get(j).startTime.substring(6, 8));
                int nextEndSecond = Integer.parseInt(responses.get(j).endTime.substring(6, 8));
                int nextStartTimeToInt = changeTimeToInteger(responses.get(j).startTime);
                int nextEndTimeToInt = changeTimeToInteger(responses.get(j).endTime);

                if(nextStartTimeToInt <= currentTime && currentTime <= nextEndTimeToInt) {
                    count++;
                }
            }

            maxCount.add(count);

            while ( startSecond == Integer.parseInt(responses.get(i).startTime.substring(6, 8))) {
                i++;
                if (i == responses.size()) break;
            }
        }

        return maxCount.peek();
    }

    private static int changeTimeToInteger(String startTime) {
        String[] split = startTime.split(":");
        return Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2].substring(0,2));
    }

    private static Response parse(String line) {
        try{
            String[] split = line.split(" ");
            String stringDate = split[0];
            String stringEndTime = split[1];

            // 시간 문자열과 시간 차이를 분리
            String timeString = line.substring(11, 23); // "20:59:57.421"
            String durationString = line.substring(24); // "0.351s"

            // 시간 문자열을 Date 객체로 변환
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss.SSS");
            Date date = timeFormat.parse(timeString);

            // BigDecimal로 추가 시간을 정확히 처리
            BigDecimal additionalSeconds = new BigDecimal(durationString.replace("s", ""));
            long additionalMillis = additionalSeconds.multiply(BigDecimal.valueOf(1000)).longValue();

            // 현재 시간에 추가된 시간을 더함 (처리 시간이 포함되도록)
            long newTimeMillis = date.getTime() - additionalMillis;

            // 새로운 시간 계산
            Date newDate = new Date(newTimeMillis + (long) 0.001);

            // 새로운 시간 출력
            String stringStartTime = timeFormat.format(newDate);

            return new Response(stringDate, stringStartTime, stringEndTime);
        } catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
