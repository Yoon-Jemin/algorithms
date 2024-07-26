package programmers.level_2;

import java.util.*;

public class 과제진행하기 {

    public static void main(String[] args) {

//        String[][] plans = {
//                {"a","09:00","30"},
//                {"b","09:10","20"},
//                {"c","09:15","20"},
//                {"d","09:55","10"},
//                {"e","10:50","5"}
//        };
        String[][] plans = {
                {"a","11:40","20"},
                {"b","12:10","30"},
                {"c","12:30","40"}
        };

        String[] result = solution(plans);

        for(String s : result) System.out.println(s);

    }
    public static String[] solution(String[][] plans) {
        Arrays.sort(plans, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return convertToMinutes(o1[1]) - convertToMinutes(o2[1]);
            }
        });

        Queue<String[]> planQueue = new LinkedList<>();
        Stack<String[]> waitStack = new Stack<>();
        planQueue.addAll(Arrays.asList(plans));

        ArrayList<String[]> planList = new ArrayList<>();

        int currentTime = 0;
        int nextJobStartTime = 0;

        while (!planQueue.isEmpty()) {
            String[] nowJob = planQueue.poll();
            currentTime = convertToMinutes(nowJob[1]);

            if (planQueue.isEmpty()) {
                planList.add(nowJob);
                break;
            } else {
                String[] nextJob = planQueue.peek();
                nextJobStartTime = convertToMinutes(nextJob[1]);
            }

            if (currentTime + Integer.parseInt(nowJob[2]) <= nextJobStartTime) { // 제 시간 안에 완료 또는 딱 완료
                currentTime += Integer.parseInt(nowJob[2]);
                planList.add(nowJob);

                while (!waitStack.isEmpty()) { // 미완료 과제 수행
                    String[] waitJob = waitStack.peek();
                    if (currentTime + Integer.parseInt(waitJob[2]) <= nextJobStartTime) { // 미완료 과제 수행 가능
                        currentTime += Integer.parseInt(waitJob[2]);
                        planList.add(waitJob);
                        waitStack.pop();
                    } else {
                        waitJob[2] = String.valueOf(Integer.parseInt(waitJob[2]) - (nextJobStartTime - currentTime));
                        currentTime = nextJobStartTime;
                        break;
                    }
                }
            } else { // 제 시간 안에 완료 X
                nowJob[2] = String.valueOf(Integer.parseInt(nowJob[2]) - (nextJobStartTime - currentTime));
                currentTime = nextJobStartTime;
                waitStack.add(nowJob);
            }
        }

        while (!waitStack.isEmpty()){
            planList.add(waitStack.pop());
        }

        String[] answer = new String[plans.length];
        for (int i = 0; i < planList.size(); i++) {
            answer[i] = planList.get(i)[0];
        }
        return answer;
    }

    private static int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}
