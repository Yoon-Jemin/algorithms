package algorithms.자료구조;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 백준 P2164
public class ex13_카드 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<Integer> myQueue = new LinkedList<>();

        int N = sc.nextInt();;

        for(int i =1; i <= N; i++){
            myQueue.add(i);
        }

        while(myQueue.size() > 1){
            myQueue.poll();
            int temp = myQueue.poll();
            myQueue.add(temp);
        }

        System.out.println(myQueue.poll());
    }
}
