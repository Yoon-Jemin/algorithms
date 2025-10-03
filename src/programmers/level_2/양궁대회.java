package programmers.level_2;

public class 양궁대회 {

    public static void main(String[] args) {
        int n = 9;
        int[] info = {0,0,1,2,0,1,1,1,1,1,1};

//        int n = 5;
//        int[] info = {2,1,1,1,0,0,0,0,0,0,0};

        int[] answer = solution(n, info);
        for (int i = 0; i < answer.length; i++) System.out.print(answer[i] + ", ");
    }

    static int maxDifference = -1;
    static int[] answer = new int[11];
    public static int[] solution(int n, int[] info) {
        int[] ryan = new int[11];
        backTrack(ryan, 0, n, n, info);

        if (maxDifference == -1) return new int[] {-1};
        return answer;
    }

    public static void backTrack(int[] ryan, int index, int arrows, int n, int[] peach) {
        if (index == 10) {
            ryan[index] = arrows;
            int difference = calculate(ryan, peach);
            if (difference > maxDifference) {
                maxDifference = difference;
                System.arraycopy(ryan, 0, answer, 0, 11);
            } else if (difference == maxDifference) {
                if (isLower(answer, ryan)) {
                    System.arraycopy(ryan, 0, answer, 0, 11);
                }
            }
            ryan[index] = 0;
            return;
        }

        // 화살이 남아 있으면 쏘기
        if (peach[index] < arrows) {
            ryan[index] = peach[index] + 1;
            arrows -= peach[index] + 1;
            backTrack(ryan, index + 1, arrows, n, peach);
            ryan[index] = 0;
            arrows += peach[index] + 1;
        }

        // 안 쏘고 넘어가기
        backTrack(ryan, index + 1, arrows, n, peach);
    }

    public static int calculate(int[] ryan, int[] peach) {
        int ryanScore = 0;
        int peachScore = 0;

        for (int i = 0; i <= 10; i++) {
            int score = 10 - i;

            if (ryan[i] == 0 && peach[i] == 0) continue;

            if (ryan[i] > peach[i]) {
                ryanScore += score;
            } else {
                peachScore += score;
            }
        }

        if (ryanScore > peachScore) return ryanScore - peachScore;
        return -1;
    }

    private static boolean isLower (int[] answer, int[] ryan) {
        boolean isLower = false;

        for (int i = 10; i >= 0; i--) {
            if (answer[i] == 0 && ryan[i] > 0) {
                isLower = true;
                break;
            } else if (answer[i] > 0 && ryan[i] == 0) {
                isLower = false;
                break;
            } else if (answer[i] > 0 && ryan[i] > 0) {
                if (answer[i] == ryan[i]) continue;
                if (answer[i] > ryan[i]) isLower = false;
                else isLower = true;
                break;
            }
        }

        return isLower;
    }

}
