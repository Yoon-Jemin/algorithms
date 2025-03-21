package programmers.level_2;

public class 거리두기 {
    public int[] solution(String[][] places) {
        int n = places.length;
        int[] answer = new int[n];

        int count = 0;
        for (String[] place : places) {
            Boolean isGood = true;

            for (int i = 0; i < 5; i++) {
                String row = place[i];

                for (int j = 0; j < 5; j++) {
                    String now = row.substring(j, j + 1);   // i, j
                    if (!now.equals("P")) continue;
                    // 거리가 1
                    if (isInside(i + 1, j)) {
                        if (place[i + 1].substring(j, j+1).equals("P")) isGood = false;
                    }
                    if (isInside(i - 1, j)) {
                        if (place[i - 1].substring(j, j+1).equals("P")) isGood = false;
                    }
                    if (isInside(i, j + 1)) {
                        if (place[i].substring(j+1, j+2).equals("P")) isGood = false;
                    }
                    if (isInside(i, j - 1)) {
                        if (place[i].substring(j-1, j).equals("P")) isGood = false;
                    }

                    // 거리가 2 (일직선)
                    if (isInside(i + 2, j)) {
                        if (place[i + 2].substring(j, j+1).equals("P") && place[i + 1].substring(j, j+1).equals("O")) isGood = false;
                    }
                    if (isInside(i - 2, j)) {
                        if (place[i - 2].substring(j, j+1).equals("P") && place[i - 1].substring(j, j+1).equals("O")) isGood = false;
                    }
                    if (isInside(i, j + 2)) {
                        if (place[i].substring(j+2, j+3).equals("P") && place[i].substring(j+1, j+2).equals("O")) isGood = false;
                    }
                    if (isInside(i, j - 2)) {
                        if (place[i].substring(j-2, j-1).equals("P") && place[i].substring(j-1, j).equals("O")) isGood = false;
                    }

                    // 거리가 2 (대각선)
                    if (isInside(i + 1, j + 1)) {
                        if (place[i+1].substring(j+1,j+2).equals("P")){
                            if (place[i+1].substring(j,j+1).equals("O") || place[i].substring(j+1,j+2).equals("O")) isGood = false;
                        }
                    }
                    if (isInside(i + 1, j - 1)) {
                        if (place[i + 1].substring(j-1, j).equals("P")){
                            if (place[i+1].substring(j,j+1).equals("O") || place[i].substring(j-1,j).equals("O")) isGood = false;
                        }
                    }
                    if (isInside(i - 1, j + 1)) {
                        if (place[i - 1].substring(j + 1, j + 2).equals("P")){
                            if (place[i - 1].substring(j,j+1).equals("O") || place[i].substring(j+1,j+2).equals("O")) isGood = false;
                        }
                    }
                    if (isInside(i - 1, j - 1)) {
                        if (place[i - 1].substring(j - 1, j).equals("P")){
                            if (place[i - 1].substring(j,j+1).equals("O") || place[i].substring(j-1,j).equals("O")) isGood = false;
                        }
                    }
                }

            }
            answer[count] = isGood ? 1 : 0;
            count++;
        }

        return answer;
    }

    private Boolean isInside(int x, int y) {
        return x < 5 && y < 5 && x > -1 && y > -1;
    }
}
