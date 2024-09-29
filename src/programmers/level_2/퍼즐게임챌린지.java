package programmers.level_2;

public class 퍼즐게임챌린지 {

    public static void main(String[] args) {

        System.out.println(solution(
                new int[]{1, 328, 467, 209, 54},
                new int[]{2, 7, 1, 4, 3},
                1723
        ));
    }

    public static int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;

        int start = 1;
        int end = 100000;

        while(start <= end) {
            int level = (start + end) / 2;

            if(check(diffs, times, limit, level)){  // 충분함 -> level을 낮춰야 함
                end = level -1;
                answer = level;
            } else{     // 초과 -> level를 높여야 함
                start = level + 1;
            }
        }

        return answer;
    }

    public static boolean check(int[] diffs, int[] times, long limit, long level){
        long sum = 0;

        for(int i = 0; i < diffs.length; i++){
            if(level >= diffs[i]){
                sum += times[i];
                if(limit < sum) return false;
            } else {
                long retry = diffs[i] - level;
                int duration = 0;
                if(i > 0){
                    duration = times[i - 1] + times[i];
                } else {
                    duration = times[i];
                }
                sum += duration * retry;
                sum += times[i];
                if(limit < sum) return false;
            }
        }

        return limit >= sum;
    }
}
