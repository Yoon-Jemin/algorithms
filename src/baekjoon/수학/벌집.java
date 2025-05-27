package baekjoon.수학;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 벌집 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int total = 1;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int plus = 6;

        while (total < n) {
            list.add(total + plus);
            total += plus;
            plus += 6;
        }

        System.out.println(list.size());
    }
}
