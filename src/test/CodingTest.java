package test;

import java.util.*;

public class CodingTest {

    // k: 300
    // limits:
    //[[2,3,-1,-1,-1], [4,0,-1,-1,6], [5,0,0,0,0], [-1,0,0,0,0], [-1,-1,-1,-1,-1], [-1,-1,0,0,0]]
    public static void main(String[] args){

        int k = 300;
        int[] limits = {2000,1000,500,200,600,500};
        int[][] sockets = {{2,3,-1,-1,-1}, {4,0,-1,-1,6}, {5,0,0,0,0}, {-1,0,0,0,0}, {-1,-1,-1,-1,-1}, {-1,-1,0,0,0}};

        int answer = solution(k, limits, sockets);
        System.out.println(answer);

    }

    public static int solution(int k, int[] limits, int[][] sockets) {
        int answer = 0;

        int size = limits.length;   // 5
        Map<Integer, int[]> usageMap = new LinkedHashMap<>();   // [key: 콘센트 번호, value: {현재 사용량, 제한 사용량}]
        Map<Integer, int[]> socketMap = new LinkedHashMap<>();  // [key: 콘센트 번호, value: {소켓 사용 현황}]

        // 데이터 초기화
        for(int i = size; i > 0; i--){
            socketMap.put(i, sockets[i-1]);
            int sum = 0;
            for(int num: sockets[i-1]){
                if(num == -1) {
                    sum += k;
                } else if (num != 0){
                    sum += usageMap.get(num)[0];
                }
            }
            int[] usage = new int[] {sum, limits[i-1]};
            usageMap.put(i, usage);
        }

        for(int i = size; i > 0; i--){
            // 업데이트 된 합계 값
            int updatedsum = 0;
            for(int num: sockets[i-1]){
                if(num == -1) {
                    updatedsum += k;
                } else if (num != 0){
                    updatedsum += usageMap.get(num)[0];
                }
            }

            int limit = usageMap.get(i)[1];
            while(updatedsum > limit){
                updatedsum -= k;
                answer++;
            }
            usageMap.get(i)[0] = updatedsum;
        }


        return answer;
    }
}
