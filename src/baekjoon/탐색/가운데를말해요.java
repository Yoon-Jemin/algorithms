package baekjoon.탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 가운데를말해요 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int next = Integer.parseInt(br.readLine());

            if (maxHeap.isEmpty() || next <= maxHeap.peek()) maxHeap.add(next);
            else minHeap.add(next);

            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }

            System.out.println(maxHeap.peek());
        }
    }
}

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int n = sc.nextInt();
//
//        List<Integer> list = new ArrayList<>();
//
//        int firstNum = sc.nextInt();
//        list.add(firstNum);
//        System.out.println(firstNum);
//
//        for (int i = 1; i < n; i++) {
//            int num = sc.nextInt();
//            int left = 0;
//            int right = list.size() - 1;
//            int mid = 0;
//
//            while (left <= right) {
//                mid = (left + right) / 2;
//                if (num > list.get(mid)) {
//                    left = mid + 1;
//                } else {
//                    right = mid - 1;
//                }
//            }
//            list.add(left, num);
//
//            left = 0;
//            right = list.size() - 1;
//            mid = (left + right) / 2;
//            System.out.println(list.get(mid));
//        }
//    }
