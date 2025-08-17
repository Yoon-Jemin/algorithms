package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 멀티탭스케줄링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());   // 멀티탭 구멍의 개수
        int m = Integer.parseInt(st.nextToken());   // 전기 용품의 총 사용 횟수

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>();
        int answer = 0;
        for (int i = 0; i < m; i++) {
            int code = arr[i];
            if (map.containsKey(code)) continue;   // 코드가 이미 꽂혀있는 경우
            else if (map.size() < n) map.put(code, 0);     // 멀티탭에 플러그가 남아있는 경우
            else {
                for (int key : map.keySet()) {
                    map.put(key, Integer.MAX_VALUE);
                }
                for (int j = i + 1; j < m; j++) {
                    int nextCode = arr[j];
                    if (map.containsKey(nextCode) && map.get(nextCode) == Integer.MAX_VALUE) {
                        map.put(nextCode, j);
                    }
                }

                int latest = -1;
                int removeKey = 0;
                for (int key : map.keySet()) {
                    if (map.get(key) > latest) {
                        latest = map.get(key);
                        removeKey = key;
                    }
                }

                map.remove(removeKey);
                map.put(code, 0);
                answer++;
            }
        }

        System.out.println(answer);
    }
}
