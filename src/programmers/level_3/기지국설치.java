package programmers.level_3;

public class 기지국설치 {

    public static void main(String[] args) {
//        int n = 11;
//        int[] stations = {4, 11};
//        int w = 1;

        int n = 16;
        int[] stations = {9};
        int w = 2;
        System.out.println(solution(n, stations, w));
    }

    public static int solution(int n, int[] stations, int w) {
        int answer = 0;
        int current = 1;
        int stationIndex = 0;

        while (current <= n) {
            if (stationIndex < stations.length && current >= stations[stationIndex] - w) {
                current = stations[stationIndex] + w + 1;
                stationIndex++;
            } else {
                answer++;
                current += w * 2 + 1;
            }
        }
        return answer;
    }
}
