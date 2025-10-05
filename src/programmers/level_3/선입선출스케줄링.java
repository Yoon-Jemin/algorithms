package programmers.level_3;

import java.util.*;

public class 선입선출스케줄링 {

    public static void main(String[] args) {
        int n = 6;
        int[] cores = {1, 2, 3};
        System.out.println(solution(n, cores));
    }

    public static int solution(int n, int[] cores) {
        if (n <= cores.length) return n;

        int left = 0;
        int right = 10000 * n;

        int time = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            long work = 0;

            for (int core : cores) {
                work += (mid / core) + 1;
            }

            if (work >= n) {
                time = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        long workDone = 0;
        for (int core : cores) {
            workDone += ((time - 1) / core) + 1;
        }

        for (int i = 0; i < cores.length; i++) {
            if (time % cores[i] == 0) {
                workDone++;
                if (workDone == n) return i + 1;
            }
        }

        return -1;
    }
}
