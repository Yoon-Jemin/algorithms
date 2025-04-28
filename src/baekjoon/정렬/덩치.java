package baekjoon.정렬;

import java.util.*;

public class 덩치 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<int[]> people = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            people.add(new int[]{sc.nextInt(), sc.nextInt(), i});
        }

        Collections.sort(people, Comparator.comparingInt(a -> a[0]));

        int[] answer = new int[n];
        for (int i = 0; i < people.size(); i++) {
            int bigger = 1;
            int weight = people.get(i)[0];
            int height = people.get(i)[1];
            for (int j = i + 1; j < people.size(); j++) {
                if (weight < people.get(j)[0] && height < people.get(j)[1]) {
                    bigger++;
                }
            }
            answer[people.get(i)[2]] = bigger;
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
