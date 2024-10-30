package programmers.level_2;

import java.util.*;

public class 주차요금계산 {

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600};
        String[] records = {
                "05:34 5961 IN",
                "06:00 0000 IN",
                "06:34 0000 OUT",
                "07:59 5961 OUT",
                "07:59 0148 IN",
                "18:59 0000 IN",
                "19:09 0148 OUT",
                "22:59 5961 IN",
                "23:00 5961 OUT"
        };

        int[] result = solution(fees, records);
        for(int i=0; i<result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public static int[] solution(int[] fees, String[] records) {

        int defaultTime = fees[0];
        int defaultPrice = fees[1];
        int extraTime = fees[2];
        int extraPrice = fees[3];

        Map<String, Car> carMap = new HashMap<>();

        for(String record : records) {
            String[] split = record.split(" ");

            String time = split[0];     // 05:34
            String carNum = split[1];   // 5961
            String status = split[2];   // IN

            if(!carMap.containsKey(carNum)) {
                Car car = new Car(carNum, time);
                carMap.put(carNum, car);
            } else {
                Car car = carMap.get(carNum);
                if(status.equals("OUT")) {
                    String strlastParkTime = car.lastParkTime;
                    String strOutTime = time;
                    int lastParkTime = timeToInt(strlastParkTime);
                    int outTime = timeToInt(strOutTime);
                    int duration = outTime - lastParkTime;
                    car.updateSumTime(duration);
                    car.updateIsParked(false);
                } else if (status.equals("IN")) {
                    car.updateIsParked(true);
                    car.updateLastParkTime(time);
                }
            }
        }

        List<Car> carList = new ArrayList<>();
        for(Car car : carMap.values()) {
            if(car.isParked){   // 아직 주차된 경우
                String strlastParkTime = car.lastParkTime;
                String strOutTime = "23:59";
                int lastParkTime = timeToInt(strlastParkTime);
                int outTime = timeToInt(strOutTime);
                int duration = outTime - lastParkTime;
                car.updateSumTime(duration);
                car.updateIsParked(false);
            }
            carList.add(car);
        }

        Collections.sort(carList, (c1, c2) -> (c1.carNum.compareTo(c2.carNum)));

        int[] answer = new int[carList.size()];
        for(int i=0; i<carList.size(); i++) {
            int sumTime = carList.get(i).sumTime;
            int fee = 0;
            if(sumTime > defaultTime) {
                if((sumTime - defaultTime) % extraTime == 0) {
                    fee = defaultPrice + ((sumTime - defaultTime) / extraTime) * extraPrice;
                } else {
                    fee = defaultPrice + (((sumTime - defaultTime) / extraTime) + 1) * extraPrice;
                }
            } else {
                fee = defaultPrice;
            }

            answer[i] = fee;
        }
        return answer;
    }

    private static int timeToInt(String strlastParkTime) {
        String[] split = strlastParkTime.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);

        return hour * 60 + minute;
    }

    static class Car{
        String carNum;
        String lastParkTime;
        int sumTime;
        boolean isParked;

        Car(String carNum, String lastParkTime) {
            this.carNum = carNum;
            this.lastParkTime = lastParkTime;
            this.sumTime = 0;
            this.isParked = true;
        }

        public void updateLastParkTime(String lastParkTime) {
            this.lastParkTime = lastParkTime;
        }

        public void updateSumTime(int time){
            sumTime += time;
        }

        public void updateIsParked(boolean isParked){
            this.isParked = isParked;
        }
    }
}
