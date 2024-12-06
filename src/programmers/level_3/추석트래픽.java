package programmers.level_3;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class 추석트래픽 {

    public static void main(String[] args) {
//        String[] lines = new String[] {
//                "2016-09-15 20:59:57.421 0.351s",
//                "2016-09-15 20:59:58.233 1.181s",
//                "2016-09-15 20:59:58.299 0.8s",
//                "2016-09-15 20:59:58.688 1.041s",
//                "2016-09-15 20:59:59.591 1.412s",
//                "2016-09-15 21:00:00.464 1.466s",
//                "2016-09-15 21:00:00.741 1.581s",
//                "2016-09-15 21:00:00.748 2.31s",
//                "2016-09-15 21:00:00.966 0.381s",
//                "2016-09-15 21:00:02.066 2.62s"
//        };

        String[] lines = new String[] {
                "2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"
        };

        System.out.println(solution(lines));
    }

    public static class Response {
        long startTime; // 시작 시간 (밀리초)
        long endTime;   // 종료 시간 (밀리초)

        public Response(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public static int solution(String[] lines) {
        List<Response> responses = new ArrayList<>();

        // 로그 파싱
        for (String line : lines) {
            responses.add(parse(line));
        }

        int maxCount = 0;

        // 각 요청의 시작과 끝 시점을 기준으로 1초 구간 처리량 계산
        for (Response response : responses) {
            maxCount = Math.max(maxCount, calculateThroughput(responses, response.startTime));
            maxCount = Math.max(maxCount, calculateThroughput(responses, response.endTime));
        }

        return maxCount;
    }

    private static int calculateThroughput(List<Response> responses, long startTime) {
        long windowEnd = startTime + 999; // 1초 구간의 끝 (밀리초)
        int count = 0;

        for (Response response : responses) {
            // 요청이 구간에 걸쳐 있는 경우
            if (!(response.endTime < startTime || response.startTime > windowEnd)) {
                count++;
            }
        }

        return count;
    }

    private static Response parse(String line) {
        try {
            String[] parts = line.split(" ");
            String dateTime = parts[0] + " " + parts[1]; // "2016-09-15 20:59:57.421"
            String durationStr = parts[2].replace("s", ""); // "2.0s" -> "2.0"

            // 종료 시간 파싱
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date endDate = sdf.parse(dateTime);
            long endTime = endDate.getTime();

            // 처리 시간(ms) 계산
            double duration = Double.parseDouble(durationStr);
            long processingTime = (long) (duration * 1000);

            // 시작 시간 계산
            long startTime = endTime - processingTime + 1; // 처리 시간을 포함하므로 +1

            return new Response(startTime, endTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
